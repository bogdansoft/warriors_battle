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

#### Branch 3-defenders

In the previous mission - Army battles, you've learned how to make a battle between 2 armies. But we have only 2 types of units - the Warriors and Knights.
Let's add another one - the Defender. It should be the subclass of the Warrior class and have an additional defense parameter, which helps him to survive 
longer. When another unit hits the defender, he loses a certain amount of his health according to the next formula:
enemy attack - self defense (if enemy attack > self defense).
Otherwise, the defender doesn't lose his health.
The basic parameters of the Defender:

    health = 60
    attack = 3
    defense = 2
    
#### Example:

```
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var mike = new Knight();
        var rog = new Warrior();
        var lancelot = new Defender();

        assert Battle.fight(chuck, bruce) == true;
        assert Battle.fight(dave, carl) == false;
        assert chuck.isAlive() == true;
        assert bruce.isAlive() == false;
        assert carl.isAlive() == true;
        assert dave.isAlive() == false;
        assert Battle.fight(carl, mark) == false;
        assert carl.isAlive() == false;
        assert Battle.fight(bob, mike) == false;
        assert Battle.fight(lancelot, rog) == true;

        var myArmy = new Army();
        myArmy.addUnits(Defender::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 2);

        var army3 = new Army();
        army3.addUnits(Warrior::new, 1);
        army3.addUnits(Defender::new, 1);

        var army4 = new Army();
        army4.addUnits(Warrior::new, 2);

        assert Battle.fight(myArmy, enemyArmy) == false;
        assert Battle.fight(army3, army4) == true;
        
```
Note: From now on, the tests from "check" part will use another type of warrior: the rookie. Its code is
```
public class Rookie extends Warrior {
    @Override
    public int getAttack() {
        return 1;
    }
}
```
