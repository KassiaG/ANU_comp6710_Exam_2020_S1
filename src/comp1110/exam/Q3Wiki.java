package comp1110.exam;

import java.util.*;

/**
 * COMP1110 Exam, Question 3.1
 * <p>
 * This class represents a wiki: a collection of linked articles that is
 * contributed to by multiple editors.
 * In this wiki, each article has a unique ID (integer), a name (String) and a
 * category (String). Multiple articles may have the same category.
 * Articles may link to other articles in the wiki; each article has at most
 * one link to each other article.
 * Each article also has a set of editors (the users who have edited the
 * article). An editor is represented by their username (String),
 * and each editor may have edited zero or more articles.
 */
public class Q3Wiki {
    /**
     * Add a new article to this wiki. If the given article ID already exists,
     * do not modify this wiki.
     *
     * @param id       the ID of the article
     * @param name     the name of the article
     * @param category the category of the article
     * @param editors  the set of editors of the article
     * @return true if the article was added to this wiki, or false if the
     * article was not added (because it already exists)
     */
    private Map<Integer, Article> articles;// Map to store articles by ID
    private Map<String, Set<Integer>> categories; // Map to store articles by category
    private Map<Integer, Set<Integer>> links;// Map to store links between articles
    public Q3Wiki(){
        articles = new HashMap<>();
        categories = new HashMap<>();
        links = new HashMap<>();
    }
    public boolean addArticle(int id, String name, String category, Set<String> editors) {
        if(articles.containsKey(id)){
            return false;
        }
        Article article = new Article(id, name, category, editors);
        articles.put(id, article);
        // Add the article to the category
        Set<Integer> categoryArticles = categories.getOrDefault(category, new HashSet<>());
        categoryArticles.add(id);
        categories.put(category, categoryArticles);
        // FIXME complete this method
        return true;
    }

    /**
     * Adds a link from one article to another in this wiki.
     *
     * @param fromArticle the article to link from
     * @param toArticle   the article to link to
     * @return true if the link was added, or false if the link already exists
     */
    public boolean addLink(int fromArticle, int toArticle) {
        // FIXME complete this method
        if (links.containsKey(fromArticle)) {
            Set<Integer> linkedArticles = links.get(fromArticle);
            if (linkedArticles.contains(toArticle)) {
                return false; // Link already exists
            }
        } else {
            links.put(fromArticle, new HashSet<>());
        }

        links.get(fromArticle).add(toArticle);
        return true;
    }

    /**
     * Delete the specified article from this wiki.
     * All information associated with this article is deleted, including
     * links to other articles and incoming links from other articles.
     *
     * @param id the ID of the article to be deleted
     * @return true if the specified article was found and deleted,
     * or false if the specified article was not found in this wiki
     */
    public boolean deleteArticle(int id) {
        // FIXME complete this method
        Article article = articles.remove(id);
        if (article == null) {
            return false; // Article not found
        }
        String category = article.getCategory();
        // Remove the article from the category
        Set<Integer> categoryArticles = categories.get(category);
        categoryArticles.remove(id);
        if (categoryArticles.isEmpty()) {
            categories.remove(category);
        }
        // Remove the article from the links
        for (Set<Integer> linkedArticles : links.values()) {
            linkedArticles.remove(id);
        }

        return true;
    }

    /**
     * @return the total number of articles in this wiki
     */
    public int getArticleCount() {

        // FIXME complete this method
        return articles.size();
    }

    /**
     * Gets the set of articles that were edited by the given editor.
     *
     * @param editor the username of the editor
     * @return the set of articles that were edited by the given editor
     * (if the editor has not edited any articles, this will be the empty set)
     */
    public Set<Integer> getArticlesEditedBy(String editor) {
        // FIXME complete this method
        Set<Integer> editedArticles = new HashSet<>();
        for (Article article : articles.values()) {
            if (article.getEditors().contains(editor)) {
                editedArticles.add(article.getId());
            }
        }
        return editedArticles;

    }

    /**
     * Gets the set of all articles for the given category.
     *
     * @param category the name of the category to search for
     * @return the set of all articles in the given category
     * (if there are no articles for the category, this will be the empty set)
     */
    public Set<Integer> getArticlesForCategory(String category) {
        // FIXME complete this method

        return categories.getOrDefault(category, new HashSet<>());
    }

    /**
     * Gets the largest number of incoming links to any article.
     * For example, if there are four articles in this wiki:
     * - "Ursula" (category: Person) links to "Earthsea" and "Darkness"
     * - "Earthsea" (category: Book) links to "Ursula" and "Ged"
     * - "Darkness" (category: Book) links to "Ursula"
     * - "Ged" (category: Person) links to "Ursula" and "Earthsea"
     * then getMaxIncomingLinks() == 3 (for the article "Ursula")
     *
     * @return the largest number of incoming links to any article
     */
    public int getMaxIncomingLinks() {
        // FIXME complete this method
        int maxIncomingLinks = 0;
        for (Set<Integer> linkedArticles : links.values()) {
            maxIncomingLinks = Math.max(maxIncomingLinks, linkedArticles.size());
        }
        return maxIncomingLinks;
    }

    /**
     * Gets the total number of links between articles of different categories.
     * For example, if there are three articles in this wiki:
     * - "Charlotte" (category: Person) links to "Jane"
     * - "Jane" (category: Book) links to "Charlotte"
     * - "Sargasso" (category: Book) links to "Charlotte" and "Jane"
     * then the number of cross-category links is 3:
     * - Charlotte -> Jane
     * - Jane -> Charlotte
     * - Sargasso -> Charlotte
     *
     * @return the total number of cross-category links
     */
    public int getNumCrossCategoryLinks() {
        // FIXME complete this method

        int crossCategoryLinks = 0;
        for (Set<Integer> linkedArticles : links.values()) {
            for (int toArticle : linkedArticles) {
                Article fromArticle = articles.get(toArticle);
                Article toArticleObj = articles.get(toArticle);
                if (!fromArticle.getCategory().equals(toArticleObj.getCategory())) {
                    crossCategoryLinks++;
                }
            }
        }
        return crossCategoryLinks;
    }

    /**
     * Get the number of different categories of article that the given editor
     * has edited.
     * For example, if editor "Okonkwo" has edited three articles:
     * - "Chinua" (category: Person)
     * - "Apart" (category: Book)
     * - "Ease" (category: Book)
     * then getNumCategoriesEdited("Okonkwo") == 2.
     *
     * @param editor the username of the editor to search for
     * @return the number of distinct categories of article that the specified editor has edited
     */
    public int getNumCategoriesEdited(String editor) {
        // FIXME complete this method
        Set<String> editedCategories = new HashSet<>();
        for (Article article : articles.values()) {
            if (article.getEditors().contains(editor)) {
                editedCategories.add(article.getCategory());
            }
        }
        return editedCategories.size();
    }

    /**
     * Get the maximum number of articles in any category.
     * For example, if there are four articles in the wiki:
     * - "Chinua" (category: Person)
     * - "Apart" (category: Book)
     * - "Ease" (category: Book)
     * - "Wrestling" (category: Sport)
     * then getMaxArticlesInCategory() == 2 (for the category "Book")
     *
     * @return the largest number of articles in any category
     */
    public int getMaxArticlesInCategory() {
        // FIXME complete this method

        int maxArticles = 0;
        for (Set<Integer> categoryArticles : categories.values()) {
            maxArticles = Math.max(maxArticles, categoryArticles.size());
        }
        return maxArticles;
    }

    private static class Article {
        private int id;
        private String name;
        private String category;
        private Set<String> editors;
        public Article(int id, String name, String category, Set<String> editors) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.editors = editors;
        }
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
        public String getCategory() {
            return category;
        }

        public Set<String> getEditors() {
            return editors;
        }

    }
}
