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
