# Word-Search-Solver
A Java program to solve any word search given.
# Installation
Java must be installed in the Windows PATH variable
* When reading the WordSearch.txt file, at line 20 the user must specify the relative path where the file is located at. In my case I had to change the file path to 
```Word-Search-Solver-master\\src\\com\\solver\\wordsearch\\WordSearch.txt```
# About Project
The program works by copying and pasting a word search inside the WordSearch.txt file. For the best result use https://puzzlemaker.discoveryeducation.com/word-search and set the output type to HTML. The program will first display the word search onto the screen and ask the user to enter the number of words to find. After the user must enter all the words that they want to find. The program will then output two coordinates for each word. Both coordinates are represented as (row, column) so a sample coordinate like (3, 5) (5,7) will mean that the first letter of the word is found at row 3, column 5 while the last letter is found at row 5 column 7. It must be stated that the program also checks for the reverse word so if a word like 'TURN' needs to be searched then the program can return the hypothetical coordinates (3, 6) (9, 12) where (3, 6) represents the letter "N" and (9, 12) represents the letter "T".  
