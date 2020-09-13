# Test list
---

#### Game
- [x] The game starts at age 4000 BC
- [x] Game age increases by 100 at every round end
- [x] Red wins at year 3000 BC
- [x] Red player starts the game
- [x] After the player red has taken their turn, it shall be player blue's turn
- [x] The two players (red and blue) alternates taking turns
- [x] All players have to take their turns before a round ends
- [ ] Exactly two players are present
- [ ] The winner is found when the game is over


#### Tiles
- [x] All Tiles are default Planes
- [x] Tile (1,0) is Ocean
- [x] Tile (0,1) is Hills
- [x] Tile (2,2) is Mountains

#### Cities
- [x] At tile (1,1) a Red city exists
- [x] At tile (4,1) a Blue city exists
- [x] A city starts with 0 production in the treasury
- [x] Any city has a population of 1
- [x] A city must always be producing something
- [x] A city must produce either archers, legions, or settlers
- [x] A city should be the same one (ref) between different turns (storing cities after creation)
- [x] A city produces 6 production per round - increase
- [x] A city's production can be changed
- [ ] Producing a unit in a city that already has a unit stationed in it will place the new unit above the city, placing more moving clockwise around the city
- [ ] Producing a unit in a city without a unit already there, will place the unit in the city
- [x] When having enough production to produce a unit the unit is produced 
- [x] The correct unit type is produced
- [ ] The units have correct production prices
- [ ] After producing a unit, the treasury will be reduced by the cost of the unit
- [ ] Setting a city's production to a Settler will result in a Settler being produced when the city reaches 30 production in the treasury
- [ ] A city's _workforce balance_ can only be set to _production_


#### Units
- [x] At game start A Red Archer is at (2,0)
- [x] At game start A Blue Legion is at (3,2)
- [x] At game start A Red Settler is at (4,3)
- [x] All units can move a maximum of 1 in distance
- [x] A unit should be the same one (ref) between different turns (storing units after creation)
- [x] Unit can be moved
- [x] Red cannot move Blue's units
- [x] Cannot stack two units with same owner at the same position
- [x] Unit can only be moved a maximum of tiles equal moveCount
- [x] Moving a unit decreases it's moveCount by the distance moved
- [x] Reset move count of all units after each round 
- [x] Moving a unit diagonally reduces moveCount by correct amount
- [x] Unit cannot be moved to an invalid tile (ocean and mountain)
- [x] Moving unit A onto tile of unit B kills unit B and A moves to tile
- [x] Moving a unit to a city owned by an opponent will change the city's ownership

- [ ] **CANNOT BE TESTED WITH MOVECOUNT OF 1** Moving a unit from (0,0) to (1,2) takes 2 distance since we move diagonally first


#### Improvements
- [ ] Set red and blue city as variables in the setup method (test file)