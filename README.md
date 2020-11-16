# Java-Programming

The project requires to design, build and document the DataBoard <E extends Data> collection. The DataBoard <E extends Data> collection is a container of generic objects that extend the Data data type. The board must ensure data privacy. A category is associated with each data on the board. The owner of the board can define his own categories and draw up a list of friends who will see the data for each type of category. The shared data are visible only for reading: friends can view the data and add a like but cannot modify it (that is up to the owner).
  
The data that I decided to implement is nothing more than a piece of music and to represent it I used the following quadruple:
<title, artist, category, likes>
where is it:
- title is a string that identifies the title of the song
- artist is a string that identifies me as the artist of the song
- category is a string that identifies the genre of the song
- likes is an integer that identifies the number of likes that that particular song has received.

The board that I have decided to implement is represented by the quadruple:
<passwBoard, <categories>, <data>, <structureFriendCategory>>
where is it:
- passwBoard is the string that identifies the unique password of the owner of the board
- <categories> is the collection of categories contained in the bulletin board
- <data> is the collection of data of type Data contained in the board
- <structureFriendCategory> represents me the structure that binds me every friend with their list of categories
visible to him.

The "structureFriendCategory" structure is different depending on the class implemented:
- in MyDataBoard I used an ArrayList of <friend, categoryFreindList> pairs;
- in MyDataboard2 I used a HashMap (k, v) where
      k = friend
      v = categoryFriendList.
