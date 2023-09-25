name('Natan').
name('Melissa').
name('Hanabi').
name('Tigreal').
name('Akai').
name('Franco').
name('Atlas').
name('Balmond').
name('Freya').
name('Saber').
name('Karina').
name('Fanny').
name('Hayabusa').
name('Nana').
name('Eudora').
name('Angela').
name('Floryn').
name('Johnson').
name('Lesley').


rank('Natan', '3' ).
rank('Melissa', '1').
rank('Hanabi', '2').
rank('Tigreal', '2').
rank('Akai','2').
rank('Franco','1').
rank('Atlas', '3').
rank('Balmond', '1').
rank('Freya', '2').
rank('Saber', '2').
rank('Karina','1').
rank('Fanny', '3').
rank('Hayabusa', '3').
rank('Balmond', '3').
rank('Nana', '1').
rank('Eudora', '3').
rank('Angela','3').
rank('Nana', '2').
rank('Floryn', '2').
rank('Johnson', '3').
rank('lesley', '3').


playerPosition('Natan', 'Marksman').
playerPosition('Melissa', 'Marksman').
playerPosition('Hanabi', 'Marksman').

playerPosition('Lesley', 'Marksman').
playerPosition('Lesley', 'Assassin').

playerPosition('Tigreal', 'Tank').
playerPosition('Tigreal', 'Support').

playerPosition('Akai','Tank').

playerPosition('Franco','Tank').
playerPosition('Franco','Support').

playerPosition('Atlas','Tank').
playerPosition('Freya','Fighter').
playerPosition('Balmond', 'Fighter').
playerPosition('Hayabusa','Assassin').
playerPosition('Karina','Assassin').
playerPosition('Fanny','Assassin').
playerPosition('Saber','Assassin').
playerPosition('Eudora','Mage' ).

playerPosition('Nana','Mage').
playerPosition('Nana','Support').

playerPosition('Floryn', 'Support').
playerPosition('Angela', 'Support').

playerPosition('Johnson', 'Tank').
playerPosition('Johnson','Support').

%пара персонажей с любыми позициями
position(X,Y):-playerPosition(X, _), playerPosition(Y, _ ), X\=Y.

%персонажи с одинаковой позицией
samePosition(X, Y):-playerPosition(X, Z), playerPosition(Y, Z), X\=Y.
%персонажи с одинаковыми рангами
sameRank(X, Y):- rank(X,Z), rank(Y,Z), X\=Y.

% могут ли игроки играть против друг друга? (условие возможности: одинаковые позиции + ранг)
opponentPair(X, Y):- samePosition(X, Y), sameRank(X, Y).

% могут ли игроки играть в паре против другой команды?(условие возможности: одинаковый ранг + разные позиции)
command(X,Y):- sameRank(X, Y), (position(X,Y))\=(samePosition(X,Y)) .

% персонажи, которые могут играть на двух позициях (например, поддержка + танк)
manyPosition(X):- (playerPosition(X, Y), playerPosition(X, Z), Y@<Z).
