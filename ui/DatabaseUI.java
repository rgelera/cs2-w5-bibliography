package ui;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import data.BibliographyDatabase;

import data.Article;
import data.Issue;
import data.Journal;

class DatabaseUI {
  private BibliographyDatabase database;
  
  DatabaseUI(BibliographyDatabase newDatabase) {
    database = newDatabase;
  }

  void runDatabase() {
    database.startDatabase();
    System.out.println("Welcome to the Bibliography Database!\n");

    while(!database.isFinished()) {
      printChoices();

      // Get input
      Scanner input = new Scanner(System.in);
      int choice = 0;
      try {
        choice = input.nextInt();
      } catch(Exception e) {
      }
      System.out.println("");

      switch(choice) {
        case 1:
          findArticlesFromYear();
          break;
        case 2:
          addArticleToIssue();
          break;
        case 3:
          findMostPublished();
          break;
        case 4:
          exitDatabase();
          break;
        default:
          System.out.println("Not a valid choice.");
      }
    }
  }

  void exitDatabase() {
    System.out.println("Bye!");
    database.stopDatabase();
  }

  void printChoices() {
    System.out.println("You may perform the following operations:");
    System.out.println("\t1) Find articles from a given year");
    System.out.println("\t2) Add an article to an issue");
    System.out.println("\t3) Find the most-published author");
    System.out.println("\t4) Exit");
    System.out.println("Please enter your choice (1, 2, or 3): ");
  }

  // Choice 1
  void findArticlesFromYear() {
    System.out.println("What year are you interested in? ");
    Scanner input = new Scanner(System.in);
    int year;
    try {
      year = input.nextInt();
    } catch (Exception e) {
      System.out.println("Invalid input");
      return;
    }
    System.out.println("");
    
    System.out.println("Here are the articles from " + year + ": ");
    ArrayList<Article> articles = database.getArticlesFromYear(year);
    if (articles.isEmpty()) {
      System.out.println("No articles from year " + year);
    } else {
      for (Article i : articles) {
        System.out.println(i.toString() + " " + i.printIssueShort());
      }
    }
  }

  // Choice 2
  void addArticleToIssue() {
    System.out.println("Here are the available issues:");
    ArrayList<Issue> issues = database.getAvailableIssues();
    printAvailableIssues(issues);

    System.out.println("What issue would you like to add to (1-" + (issues.size()) + "): ");
    Scanner input = new Scanner(System.in);
    int issueChoice;
    try {
      issueChoice = input.nextInt();
    } catch (Exception e) {
      System.out.println("Invalid input");
      return;
    }
    System.out.println("");

    if (issueChoice > issues.size()) {
      System.out.println("Invalid input, returning to main menu");
      return;
    }
    Issue issueAddTo = issues.get(issueChoice - 1);

    System.out.println("Please specify the article title: ");
    String newArticleTitle = input.next();
    System.out.println("Please specify the article author: ");
    String newArticleAuthor = input.next();

    System.out.println("");
    database.addArticleToIssue(newArticleTitle, newArticleAuthor, issueAddTo);
  }

  void printAvailableIssues(ArrayList<Issue> issues) {
    for (int i = 0; i < issues.size(); i++) {
      System.out.println((i + 1) + ") " + issues.get(i).toStringLong());
    }
  }

  // Choice 3
  void findMostPublished() {
    ArrayList<Article> articles = database.getAvailableArticles();
    Map<String, Integer> hm = new HashMap<String, Integer>();

    for (Article i : articles) {
      String auth = i.getAuthor();
      Integer j = hm.get(auth);
      hm.put(auth, (j == null) ? 1 : j + 1);
    }

    Entry<String, Integer> maxEntry = Collections.max(hm.entrySet(), (Entry<String, Integer> e1, Entry<String, Integer> e2) -> e1.getValue()
        .compareTo(e2.getValue()));

    System.out.println("The most published author is " + maxEntry.getKey());
    System.out.println("");
  }
  

  public static void main(String[] args) {
    BibliographyDatabase db = new BibliographyDatabase(Journal.getSampleJournals());
    DatabaseUI ui = new DatabaseUI(db);
    ui.runDatabase();
  }
}
