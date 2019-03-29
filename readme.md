# Project 4: Language
In this project, you will use data files of social media posts to generate slogans about a topic. Given a corpus of social media tweets, you will use an external tokenizer class (provided for you) to separate each tweet into a collection of tokens. You will then use the tokens to compile a list of [*bigrams*](https://en.wikipedia.org/wiki/Bigram). From these bigrams, you will be able to construct acronym slogans that sound something like real speech. A large portion of this project is improving the efficiency of the approach from something that works, but takes a long time, to something that works much more efficiently. Ensure that you do the recommended background research early so that you can come up with a good plan for your code and therefore have to rewrite less of it.

You are being provided with a data set of 5113 tweets in a file (*full-corpus.csv* - you may remember this set from a previous project). This is a comma-separated value file (.csv) which is a human-readable file type in which each line contains data. Pieces of data are separated by a comma, and for this data set, there are five pieces of data (i.e., fields) per line. You can open up the file in any text editor (e.g., Sublime) or even Excel if you want to look at the data directly (and you should always inspect your data!). The first line contains the name of the fields: "Topic" (what is this tweet about?), "Sentiment" (is this tweet positive about that topic, negative, neutral, or irrelevant?), "TweetId" (a unique ID used by Twitter - all of these will be different), "TweetDate" (when did this get tweeted?), and "TweetText" (what did the tweet actually say?). **Note - In this project, we will only be using the TweetText.**

**Notice - these are real tweets from real twitter users (or bots!), and do not reflect the view of your instructor, department, or university. Tweets may contain foul language  or worse. Do not follow the links associated with the profiles or tweets...just, don't.**

# Teams
Teams have been assigned for this project as follows. They are fixed - no switching or cooperating across team lines. It is up to teammates to ensure that their partner adheres to the <a href="https://www.american.edu/academics/integrity/code.cfm">American University Honor Code.</a>

> Even though you are working in teams, you **must ensure** that all team members contribute to the repository. Even if you are using paired programming, the same teammate cannot be the driver every time. Teams in which only one member commits code to the repo will be significantly penalized.

## Step 0 - Background Research.
1. **Both members** should review Section 22.9 in the textbook on the Eight Queens problem using backtracking. It bears striking similarity to the requirements for this project.
1. **Both members** should review Chapter 21 in the textbook on Sets and Maps. We will discuss this chapter in class, but this project relies heavily on this chapter so keep your attention focused here.

## Step 1 - Import and Clone the Repo for your Team.
1. **Both members** of your team will visit <a href='https://classroom.github.com/g/aQpRBfop'> this link</a>. This will import a repo for your team to Github. For this assignment, you will share the Github repo with your teammate. If you're the first member of your team to visit the link, you can create the team and import the repo - **make sure you create the right team**. If you're the second member to click the link, then **make sure** you join the right team.
2. **Both members** will clone the repository to your local machines. You will then each have a local repository that is linked to the shared repository, and can work on the code together.
3. As a reference for how to use git, I suggest <a href='http://codingdomain.com/git/'>this site</a>, as it avoids some of the more complicated theory behind git and focuses on the bare minimum practicalities.

## Step 2 - Review the provided code
You are starting with the bare minimum to read the two data files and create objects from the data in each of them. There is a slight difference for the parsing methods in the provided code this time. We are using an external library, the Twokenizer.java (Twitter + Tokenizer, get it?) to split each tweet into a collection of words, hashtags, URLs, etc. The TweetParser class has a start for parsing tweets:

```java
public class TweetParser{
  public static ArrayList<Token> parseTweets(String filename) throws Exception {
    ArrayList<Token> tokens = new ArrayList<Token>();
    Twokenizer twokenizer = new Twokenizer();
    Scanner scanner = new Scanner(new File(filename), "UTF-8");
    String line = scanner.nextLine(); //the first line is field headers, we do not want that.
    Token prevToken = null;
    while(scanner.hasNextLine()){
      line = scanner.nextLine();
      String[] fields = line.split("\",\"");

      List<String> twokens = null; //You need to call the correct method here.

      twokens.forEach(e -> {
        Token nt = new Token(e);
        tokens.add(nt);
      });
    }

    return tokens;
  }
}
```

## Step 3 - Finish the parsing methods.
Most of the work is done for you here, but to tokenize (*twokenize*) the tweet you will need to call the right method. Review the Twokenizer.java source file to determine which method to call.

## Step 4 - Compile a collection of bigrams.
As you parse each line, you will be able to construct `Token` objects from the individual Strings. You will add these tokens to a collection. However, you need to make sure that if a Token already exists for the given String, you do not add another token to your collection.  More importantly, you will track the sequence of tokens in each tweet and, for each token, compile a list of tokens that follow it in speech. For example, examine the following sentence:

> a man, a plan, a canal, panama

For the above sentence, the *twokenizer* would provide a String list something like this: ["a", "man", "a", "plan", "a", "canal", "panama"]

Some of these Strings repeat, so our collection of tokens should only contain: ["a", "man", "plan", "canal", "panama"]

And if we are recording bigrams, there are six in the above sentence:
1. "a man"
1. "man a"
1. "a plan"
1. "plan a"
1. "a canal"
1. "canal panama"

Which means, for each of the tokens in the collection, we have a list of the possible tokens that follow it in the tweet:
1. `"a" : ["man", "plan" "canal"]`
1. `"man" : ["a"]`
1. `"plan" : ["a"]`
1. `"canal" : ["panama"]`
1. `"panama" : null`

So, as you are parsing tweets in the corpus, you should be composing a collection of tokens. For each token, you should compose a collection of bigrams (or at least, a collection of the possible tokens that follow it). The current implementation uses an ArrayList for the list of tokens found in the tweets and the list of grams that follow each token.

## Step 4 1/2 - Commit and Push to Github
Remember, this is not like submitting a project to Blackboard all at the end. Commit / Push early and often to establish a rhythm with your partner(s). Do not wait until the last minute! While you are working on each step below, you should be backing up your work incrementally - save early, save often.

## Step 5 - Create a recursive method to create an acronym slogan.
Given a word, name, or even nonsense phrase (ignore spaces), you should turn that word into a phrase in which the first letter of each word in the phrase starts with the same letter from the word, in order. For example, the word "scuba" is an acronym that stands for "Self-Contained Underwater Breathing Apparatus".

So, when we choose the first word, we are going to select one of our available tokens. For the provided word "MAP", using the sentence we were examining in the previous step, only one token starts with the first character 'm' (**m**ap). Therefore, our first attempt at creating a slogan starts with "man".

However, we want our slogan to sound like a real sentence (or close to one). So, we are going to look at the list of potential bigrams for "man". We are in luck! One of them starts with 'a', which is also the first character in the second word in our acronym for m**a**p. This brings us to our last choice, finding an appropriate word for the character 'p' in ma**p**. Again, we can see that of the available bigrams that start with the token "a", one of them starts with a 'p':"plan". That leaves us with a solution to our acronym: (MAP - "man a plan").

You will likely need to write one or more recursive helper functions to accomplish this within the SloganMaker class.

## Step 6 - Utilize backtracking to find a solution if one exists
In the previous example, we were able to find a solution for "map" pretty easily with our first choices for each letter. However, if we were looking for a solution for the phrase "pam", we have two options for our starting character 'p' in **p**am. If we start with "panama" as our first token, we get stuck pretty quickly - there are no bigrams for panama, let alone one that starts with 'a'. So, it's a dead end. We don't want to return an error, or stop here, though. This is where *backtracking* comes in - an approach for searching for a candidate solution incrementally, abandoning that option as soon as it determines that the candidate cannot possibly be a valid solution, and then looking for a new candidate. You are going to want to design a recursive solution for identifying potential bigrams, adding them to a passed list, and abandoning previous candidates if no solution is available. With our current example, we would see in our method that "panama" has no available bigrams, so we would backtrack to a different candidate - "plan". Plan has a bigram that starts with 'a' - the word "a". Of the available bigrams for "a", one starts with the character 'm' - the word "man". This gives a solution that works (PAM - "plan a man").

In the material for review in this project (Section 22.9 - The Eight Queens Problem) you will find a description for a very similar problem that is extremely similar to the challenge of solving this project. In this project, you will be adapting the solution described in this section in two ways - first, you will be finding bigram sequences for a given acronym; and second, you will be using recursion rather than iteration. Your system should also be able to report back to the user if no solution can be found.

## Step 6 1/2 - Commit and Push to Github
I know I'm beginning to sound like a broken record, if a broken record could be used to provide instructions for homework projects in text form on Github, but this is important. Commit and push early and often.

## Step 7 - Optimize the efficiency of the Token and Bigram Search.
An ArrayList works okay as a Collection for this project, but we have to perform a linear search for any token or bigram. Because the ArrayList is unsorted, our performance is fairly unpredictable, and for a long acronym it could take far too many operations to find out whether there is a solution or not. Also, because ArrayList has no restrictions on containing multiple instances of the same type of Object, we have to perform a linear search ourselves to see if a Token is already there or not.

Review Chapter 21 - *Sets and Maps* for some ideas on how to improve the performance of the Token and Bigram steps in your parsing method. We will discuss these concepts in class, but you have a lot of options here for improving the performance so that we are not constantly searching over an unordered list.

Sets, for example, are much like lists but they only contain one of each type of object. That makes it much easier to ensure quickly that only one of each token is in a list. Sets are unordered, however, so it makes it more difficult to move from one possible candidate for a letter to others in alphabetical sequence. A LinkedSet, however, has an order while also restricting its contents to non-duplicate items. A TreeSet goes further by guaranteeing that its contents are sorted.

Maps, on the other hand, use the notion of pairs of keys and values. A key is like an index, and a Map contains only one of each key. When you store a value in the map, you also assign a key (index) that can be used to rapidly retrieve that value. The metaphor that is often used is a dictionary - a word is the key and the list of definitions is the value. For our purposes, if you choose to use a Map to improve the efficiency of the search, the *key* is a token, and the *value* is a list of tokens that follow the key in bigrams. Just like Sets, Maps come in several concrete varieties: the standard HashMap, which is efficient for rapid storage and retrieval; a LinkedHashMap, which maintains the entries in a a specified order (usually the order in which they were added); and a TreeMap, which is efficient for traversing the keys in a sorted order.

## Step 8 - Improve the quality of solutions.
Currently, our output sounds a little like sentences but is not quite there yet. We are using bigrams for adjacent tokens in our solution, which means that we are certain that any two words next to each other in our slogan is guaranteed to be a combination of two words that a twitter user put in the same sequence in a tweet from our corpus. But, Twitter users say a lot of weird things, and just because a bigram is found once does not mean that it is a common phrase, so our slogans can end up sounding weird.

To fix this, we need some measure of the frequency of tokens and bigrams in our corpus:
- If a Token appears more often in the corpus overall, it should be our first candidate for the first word in our solution slogan. If that token does not lead to a solution and we need to backtrack, we should move on to the next most frequent token that starts with the required character, and so on.
- If a token follows a candidate token more often in the corpus, meaning that it is a more frequently occurring bigram, it should be our first candidate for searching as we recursively attempt to complete the slogan. If that bigram does not lead to a solution, we should move on to the next most frequent bigram, and so on.

## Step 9 - Filter Tokens.
Many of the tokens that are taken from the tweets are not useful - URLs, sequences of punctuation and symbols, etc. When collecting tweets and bigrams from the corpus, update the code so that it only includes actual words and phrases.

To do this, you will need to download a dictionary file of English words. Again, you have some options here. If you want to stick to more formal words and phrases, an option like [this](https://github.com/dwyl/english-words) can give you a lot to work with. There are a number of [other sites](http://wordlist.aspell.net/) that allow you to download dictionary lists for this purpose. I recommend any simple text file where each line is a different word.

To satisfy this Step, you will need to parse this additional file in first and construct a list of allowable Strings that can be used to create Tokens. As you are iterating through tweets to construct Tokens, you will check against this list to ensure that the token is an allowable English word.

## Step 10 - Push to Github
Do not forget to push your final submission to Github before the deadline.

# Grading
Grading will be assigned according to the following categories. As described in the syllabus, each category can receive a  &#10003; (satisfactory); a &#10003;+ (above and beyond); a &#10003;- (incorrect, incomplete, or sloppy); or in the worst case an &#10005;, meaning it was altogether missing or inappropriate (assigned at the instructor's discretion). A category assigned an &#10005; can carry significant grade penalties for this assignment.

1. Repository / Teamwork. Everyone contributed and used the repository well.
2. Comments and Code Formatting. The code is easy to follow and understand.
3. Project Design. The code is designed to do what the project requires.
4. Implementation. The code does what it was designed to do.

| Rating | Repository | Form | Design | Implementation |
|-----------|-----------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| &#10003;+ | Evidence of branching and merging. | Project is commented and styled according to [Javadoc](https://www.oracle.com/technetwork/java/javase/documentation/index-137868.html) Guidelines. | Project includes all steps. | Project is free of logical and syntax errors; extensive and creative use of Java commands and classes to reduce length and/or complexity. |
| &#10003; | Multiple commits from each teammate | Project is commented and styled neatly. | Project includes working steps 1-7 with a visible attempt to complete the remaining steps. | Project compiles |
| &#10003;- | Evidence that one teammate did most of the work and/or all commits were through web interface | Comments are sparse and/or code styling is difficult to follow. | Project does not utilize recursion, backtracking, or Sets/Maps to generate solutions. | Project compiles with some minor correction |