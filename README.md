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
```java
public class Rookie extends Warrior {
    @Override
    public int getAttack() {
        return 1;
    }
}
```

#### 4-vampire

So we have 3 types of units: the Warrior, Knight and Defender. Let's make the battles even more epic and add another type - the Vampire!

Vampire should be the subclass of the Warrior class and have the additional vampirism parameter, which helps him to heal himself. When the Vampire hits 
the other unit, he restores his health by +50% of the dealt damage (enemy defense makes the dealt damage value lower).

The basic parameters of the Vampire:

    health = 40
    attack = 4
    vampirism = 50%
You should store vampirism attribute as an integer (50 for 50%). It will be needed to make this solution evolve to fit one of the next challenges of this saga.

#### 5-lancer

It seems that the Warrior, Knight, Defender and Vampire are not enough to win the battle. Let's add one more powerful unit type - the Lancer.

Lancer should be the subclass of the Warrior class and should attack in a specific way - when he hits the other unit, he also deals a 50% of the deal damage to the enemy unit, standing behind the firstly assaulted one (enemy defense makes the deal damage value lower - consider this).

The basic parameters of the Lancer:
  health = 50
  attack = 6
  
#### 6-healer

The battle continues and each army is losing good warriors. Let's try to fix that and add a new unit type - the Healer.

Healer won't be fighting (his attack = 0, so he can't deal the damage). But his role is also very important - every time the allied soldier hits the enemy, the Healer will heal the ally, standing right in front of him by +2 health points with the heal() method. Note that the health after healing can't be greater than the maximum health of the unit.
For example, if the Healer heals the Warrior with 49 health points, the Warrior will have 50 hp, because this is the maximum for him.

The basic parameters of the Healer:
  health = 60
  attack = 0
  
#### 7-straight fight

A new unit type won’t be added in this mission, but instead we’ll add a new tactic - straightFight(army1, army2). It should be the method of the Battle class and it should work as follows:

at the beginning there will be a few duels between each pair of soldiers from both armies (the first unit against the first, the second against the second and so on).
After that all dead soldiers will be removed and the process repeats until all soldiers of one of the armies will be dead.
Armies might not have the same number of soldiers. If a warrior doesn’t have an opponent from the enemy army - we’ll automatically assume that he’s won a duel (for example - 9th and 10th units from the first army, if the second has only 8 soldiers).

It's very important to note that the special abilities of the Lancer and Healer do not work in a straight fight - nobody is standing in front of or behind any of their allies, so there's nobody for the Lancer to deal extra damage to or the Healer to heal.

#### 8-weapons

In this mission you should create a new class Weapon(health, attack, defense, vampirism, healPower) which will equip your soldiers with weapons. Every weapon's object will have the parameters that will show how the soldier's characteristics change while he uses this weapon. Assume that if the soldier doesn't have some of the characteristics (for example, defense or vampirism), but the weapon have those, these parameters don't need to be added to the soldier.

The parameters list:
health - add to the current health and the maximum health of the soldier this modificator;
attack - add to the soldier's attack this modificator;
defense - add to the soldier's defense this modificator;
vampirism - increase the soldier’s vampirism to this number (in %. So vampirism = 20 means +20%);
healPower - increase the amount of health which the healer restore for the allied unit.

All parameters could be positive or negative, so when a negative modificator is being added to the soldier’s stats, they are decreasing, but none of them can be less than 0.

Let’s look at this example: vampire (basic parameters: health = 40, attack = 4, vampirism = 50%) equip the Weapon(20, 5, 2, -60, -1). The vampire has the health and the attack, so they will change - health will grow up to 60 (40 + 20), attack will be 9 (4 + 5). The vampire doesn’t have defense or the healPower, so these weapon modificators will be ignored. The weapon's vampirism modificator -60% will work as well. The standard vampirism value is only 50%, so we’ll get -10%. But, as we said before, the parameters can’t be less than 0, so the vampirism after all manipulations will be just 0%.

Also you should create a few standard weapons classes, which should be the subclasses of the Weapon. Here’s their list:
    Sword - health +5, attack +2
    Shield - health +20, attack -1, defense +2
    GreatAxe - health -15, attack +5, defense -2, vampirism +10%
    Katana - health -20, attack +6, defense -5, vampirism +50%
    MagicWand - health +30, attack +3, healPower +3
And finally, you should add an equipWeapon(Weapon weapon) method to all of the soldiers classes. It should equip the chosen soldier with the given weapon.
This method also should work for the units in the army. You should be referenced by position from the head (the front warrior is considered to be at position 0):
myArmy.equipWarriorAtPosition(0, new Sword()) - equip the first soldier with the sword.

Notes:
While healing (both vampirism and health restored by the healer), the health can’t be greater than the maximum amount of health for this unit (with consideration of all of the weapon's modificators).
If the heal from vampirism is float (for example 3.6, 1.1, 2.945), round it down in any case. So 3.6 = 3, 1.1 = 1, 2.945 = 2.
Every soldier can be equipped with any number of weapons and get all their bonuses, but if he wears too much weapons with the negative health modificator and his health is lower or equal 0 - he is as good as dead, which is actually pretty dead in this case.

#### 9-warlord

In this mission you should add a new class Warlord(), which should be the subclass of the Warrior class and have the following characteristics:
health = 100
attack = 4
defense = 2

Also, when the Warlord is included in any of the armies, that particular army gets the new moveUnits() method which allows to rearrange the units of that army for the better battle result. The rearrangement is done not only before the battle, but during the battle too, each time the allied units die. The rules for the rearrangement are as follow:
If there are Lancers in the army, they should be placed in front of everyone else.
If there is a Healer in the army, he should be placed right after the first soldier to heal him during the fight. If the number of Healers is > 1, all of them should be placed right behind the first Healer.
If there are no more Lancers in the army, but there are other soldiers who can deal damage, they also should be placed in first position, and the Healer should stay in the 2nd row (if army still has Healers).
Warlord should always stay way in the back to look over the battle and rearrange the soldiers when it's needed.
Every army can have no more than 1 Warlord.
If the army doesn’t have a Warlord, it can’t use the moveUnits() method. (it should do nothing in such case)

#### 10-creative task

You need to describe the idea (as a specification), provide basic smoke test, implement the idea, provide a suit of more granular tests to cover your use cases.


Possible ideas:
- werewolves that switch class (Defender / Vampire) depending on the day / night cycle (provided by Sun singleton);
- Sun and Moon cycles that govern behavior of some classes;
- destructible weapon;
- poisoning or burning damage;
- classes with the ability to sacrifice for mass healing or mass damage;
- cannons or archers;
- warlords-necromancers;
- warlords who can evacuate wounded warriors to rare line to be healed;
- dragons;
- character stealing classes;
- character stealing weapon;
- asynchronous battle on two or more fighting areas with logs;
