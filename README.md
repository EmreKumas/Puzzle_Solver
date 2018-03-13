This **JAVA** program tests whether a two-dimensional list has a given string, either horizontally, vertically or diagonally. Simply you give *a word as input* and the program searches that word in a two-dimensional array which is the *puzzle* itself.

When the program starts, it firstly asks if you are using the *IntelliJ IDEA* or not. The reason for it is I added a *feature which colorizes the wanted word* in the puzzle because it is hard to see the word in big puzzles. But this feature only works in IntelliJ IDEA IDE as far as I tested. So when the program starts, you specify if you use it or not to prevent any bugs.

Then, you need to give the word which will be searched. Its size doesn't matter. And after that, the program gives *4 options* for you to choose. First one is, *do it with random numbers and letters(One Time)*. When you select this option, you need to enter the row and column size of the puzzle. After that, the program creates a puzzle in the specified size and searches the given word. Lastly, it *outputs if the word is found* or not.

The second option, *do it with random numbers and letters(Until found)*. This is very similar to the first option. The difference between them is the first option only creates one puzzle and searches the word in it. But the second option *creates puzzles and searches the word in them until found*. Especially, if you give a long word as input, it can take time to find the specified word in randomly created puzzles.

The third option, *do it with a predetermined matrix(Search TAB)*. This option doesn't want row and column sizes. It uses a *predetermined matrix* as puzzle and searches the word in it. As the title says, it is best to *search the word "TAB" to see the programs capabilities*.

In these three options, every time you call one of them it creates a puzzle with *random letters and numbers*. So it can be very tricky to find long words even it may not be able to find it if it is really really long. So there is a fourth option.

The fourth option, *do it manually. (Program will add the string manually.)*. In this option, after specifying the row and column sizes the program randomly determines a place and *places the given word* if there is enough space for it to fit. This is a *recursive method* and as long as there is enough space, it will fit the word in the puzzle.

I created the program *to find all the matches* if there are more than one. If you are using the IntelliJ IDEA, you can see the results better.

If you are reading this file, most probably you know how to compile this JAVA program and execute it. But if you don't know it, I have a *Wiki page* for you to be with US. **Visit it**.