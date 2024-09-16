# homeexam2k24

This is the home exam for the course [D7032E Software engineering](https://www.ltu.se/utbildning/kurs/d70/d7032e-programvaruteknik) for fourth year students in Computer Science at Luleå tekniska universitet.

## How to run

1. Compile the game with `javac -cp .:json.jar PointSalad.java`

2. Run the server with `java -cp .:json.jar PointSalad` or with `java -cp .:json.jar PointSalad [#Players] [#Bots]` for specifying the number of players and bots. (First one will let you set number of players and bots in the beginning of the game)

3. Connect an "online" client player with `java -cp .:json.jar PointSalad 127.0.0.1`
