package comp1110.exam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * COMP1110 Final Exam, Question 1iii
 */
public class Q1MealPlan {
    /**
     * Each food is assigned to one of four groups.
     */
    public enum Meal {
        BREAKFAST, LUNCH, DINNER, DESSERT
    }

    public static class Food {
        String dish;
        Meal meal;

        public Food(String dish, Meal meal) {
            this.dish = dish;
            this.meal = meal;
        }

        @Override
        public String toString() {
            return dish + " (" + meal.name().toLowerCase() + ")";
        }
    }

    /**
     * Get all meal plans that can be composed from the provided
     * set of foods for the given number of meals.
     * A meal plan is a sequence of foods that are eaten sequentially.
     * One food is eaten per meal, and any food may only be eaten at most once.
     * Foods must be eaten in order of their Meal type according to the following rules:
     * - a BREAKFAST may only be followed by a LUNCH food;
     * - a LUNCH may only be followed by a DINNER food;
     * - an DINNER may only be followed by a DESSERT food; and
     * - a DESSERT may only be followed by a BREAKFAST food.
     * <p>
     * For example, the call
     * getAllMealPlans([Cake (dessert), Risotto (dinner)], 2)
     * returns a set containing a single plan:
     * - [Risotto (dinner), Cake (dessert)]
     * because a DINNER meal may only be followed by a DESSERT meal.
     * <p>
     * The call
     * getAllMealPlans([Cake (dessert), Vegetable Soup (lunch), Waffles (breakfast), Potato Gratin (dinner)], 4)
     * returns a set containing four plans:
     * - [ Waffles (breakfast), Vegetable Soup (lunch), Potato Gratin (dinner), Cake (dessert)]
     * - [Vegetable Soup (lunch), Potato Gratin (dinner), Cake (dessert), Waffles (breakfast)]
     * - [Potato Gratin (dinner), Cake (dessert), Waffles (breakfast), Vegetable Soup (lunch)]
     * - [Cake (dessert), Waffles (breakfast), Vegetable Soup (lunch), Potato Gratin (dinner)]
     * <p>
     * If no valid meal plan can be found, an empty list is returned.
     * For example, the call:
     * getAllMealPlans([Apple Pie (dessert), Sushi (lunch)], 2)
     * returns an empty set, because a DESSERT food cannot be followed by
     * a LUNCH food, and a LUNCH food cannot be followed by a DESSERT food.
     *
     * @param foods    the set of foods from which to construct a meal plan
     * @param numMeals the number of meals
     * @return the set of all possible meal plans of the provided foods for the
     * given number of meals
     */
    //generates all possible meal plans given a set of foods and the number of meals
    public static Set<List<Food>> getAllMealPlans(Set<Food> foods, int numMeals) {
        // FIXME complete this method
        //initializes an empty set to store the meal plans
        Set<List<Food>> mealPlans = new HashSet<>();
        //A meal plan is a sequence of foods that are eaten sequentially.
        //set up empty current meal plan to keep track of the current plan being generated
        List<Food> currentPlan = new ArrayList<>();
        //generate the meal plans recursively
        generateMealPlans(foods, numMeals, currentPlan, mealPlans);
        return mealPlans;
    }

    private static void generateMealPlans(Set<Food> foods, int numMeals, List<Food> currentPlan, Set<List<Food>> mealPlans) {
        //base case
        // the desired number of meals has been reached
        if (numMeals == 0) {//the current plan is added to the set of meal plans
            mealPlans.add(new ArrayList<>(currentPlan));
            return;
        }
        for(Food food : foods){
            if(isValidNextMeal(food, currentPlan)){
                //if it is valid, add to current plan
                currentPlan.add(food);
                Set<Food> remainingFoods = new HashSet<>(foods);
                //removes it from the set of remaining foods
                remainingFoods.remove(food);
                //makes a recursive call to generate the remaining meal plans with one less meal
                generateMealPlans(remainingFoods, numMeals - 1, currentPlan, mealPlans);
                //removes the last food added to the current plan to backtrack and try other food options
                currentPlan.remove(currentPlan.size() - 1);

            }
        }
    }

    private static boolean isValidNextMeal(Food food, List<Food> currentPlan) {
        if (currentPlan.isEmpty()) {
            return true;
        }
        Food lastFood = currentPlan.get(currentPlan.size() - 1);

        switch (lastFood.meal) {
            case BREAKFAST:
                return food.meal == Meal.LUNCH;
            case LUNCH:
                return food.meal == Meal.DINNER;
            case DINNER:
                return food.meal == Meal.DESSERT;
            case DESSERT:
                return food.meal == Meal.BREAKFAST;
            default:
                return false;
        }
    }
        public static void main(String[] args) {
            Set<Food> foods = new HashSet<>();
            foods.add(new Food("Cake", Meal.DESSERT));
            foods.add(new Food("Vegetable Soup", Meal.LUNCH));
            foods.add(new Food("Waffles", Meal.BREAKFAST));
            foods.add(new Food("Potato Gratin", Meal.DINNER));

            int numMeals = 4;
            Set<List<Food>> mealPlans = getAllMealPlans(foods, numMeals);

            for (List<Food> mealPlan : mealPlans) {
                System.out.println(mealPlan);
            }
        }
    }


