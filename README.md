### warriors_battle

#### Branch 1-warriors

In this mission (and in several subsequent ones, related to it) you’ll have a chance "to sit in the developer's chair" and create the logic of a simple game 
about battles.

Let's start with the simple task - one-on-one duel. You need to create the class Warrior, the instances of which will have 2 parameters - health (equal to 50 points) 
and attack (equal to 5 points), and 1 property - isAlive, which can be True (if warrior's health is > 0) or False (in the other case). In addition you have to create 
the second unit type - Knight, which should be the subclass of the Warrior but have the increased attack - 7. Also you have to create a static function fight() 
(in class Battle), which will initiate the duel between 2 warriors and define the strongest of them. The duel occurs according to the following principle:

Every turn, the first warrior will hit the second and this second will lose his health in the same value as the attack of the first warrior. After that, if he is 
still alive, the second warrior will do the same to the first one.

The fight ends with the death of one of them. If the first warrior is still alive (and thus the other one is not anymore), the function should return True, False 
otherwise.

##### Example:

``` 
    var chuck = new Warrior();
    var bruce = new Warrior();
    var carl = new Knight();
    var dave = new Warrior();
    assert Battle.fight(chuck, bruce) == true;
    assert Battle.fight(dave, carl) == false;
    assert chuck.isAlive() == true;
    assert bruce.isAlive() == false;
    assert carl.isAlive() == true;
    assert dave.isAlive() == false;
```

#### Branch 2-armies

In the previous mission - Warriors - you've learned how to make a duel between 2 warriors happen. Great job! But let's move to something that feels a little 
more epic - the armies! In this mission your task is to add new classes and functions to the existing ones. The new class should be the Army and have the method addUnits() - for adding the chosen amount of units to the army. The first unit added will be the first to go to fight, the second will be the second, ...

Also you need to create a Battle class with a fight() function, which will determine the strongest army.

The battles occur according to the following principles: 
at first, there is a duel between the first warrior of the first army and the first warrior of the second army. As soon as one of them dies - the next warrior 
from the army that lost the fighter enters the duel, and the surviving warrior continues to fight with his current health. This continues until all the soldiers 
of one of the armies die. In this case, the fight() function should return True, if the first army won, or False, if the second one was stronger.
Note that army 1 have the advantage to start every fight!

##### Example:

```
var chuck = new Warrior();
var bruce = new Warrior();
var carl = new Knight();
var dave = new Warrior();
var mark = new Warrior();

assert Battle.fight(chuck, bruce) == true;
assert Battle.fight(dave, carl) == false;
assert chuck.isAlive() == true;
assert bruce.isAlive() == false;
assert carl.isAlive() == true;
assert dave.isAlive() == false;
assert Battle.fight(carl, mark) == false;
assert carl.isAlive() == false;

var myArmy = new Army();
myArmy.addUnits(() -> new Knight(), 3);

var enemyArmy = new Army();
enemyArmy.addUnits(() -> new Warrior(), 3);

var army3 = new Army();
army3.addUnits(() -> new Warrior(), 20);
army3.addUnits(() -> new Knight(), 5);

var army4 = new Army();
army4.addUnits(() -> new Warrior(), 30);

assert Battle.fight(myArmy, enemyArmy) == true;
assert Battle.fight(army3, army4) == false;
```
