package nl.avans.ivh11.a2b.service.battle;

import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.domain.battle.ActionCommand;
import nl.avans.ivh11.a2b.domain.battle.Battle;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("battleService")
@Repository
@Transactional
public class BattleService {

    private CharacterRepository characterRepository;
    private EnemyRepository enemyRepository;
    private Battle battle;
    private int turnCounter = 0;

    @Autowired
    public BattleService(CharacterRepository characterRepo, EnemyRepository enemyRepo) {
        this.characterRepository = characterRepo;
        this.enemyRepository = enemyRepo;
    }

    public void startBattle() {
        battle = new Battle();
    }

    public Character findCharacter(long id) {
        return characterRepository.findOne(id);
    }

    public Enemy findEnemy(long id) {
        return enemyRepository.findOne(id);
    }

    public String battleAction(Character character, Enemy enemy) {
        String message = "";

        character.setActionBehavior(new NormalAttack());

        int oldHp = 0;
        int newHp = 0;
        int damage = 0;
        String attacker = "";
        String defender = "";

        // Validate who turns it is
        if(turnCounter % 2 == 0) {
            attacker = character.getName();
            defender = enemy.getName();
            oldHp = enemy.getStats().getCurrentHitpoints();

            // Character attacks enemy
            battle.playTurn(new ActionCommand(character, enemy));

            newHp = enemy.getStats().getCurrentHitpoints();

            saveEnemy(enemy);

        } else {
            attacker = enemy.getName();
            defender = character.getName();
            oldHp = character.getStats().getCurrentHitpoints();

            // Enemy attacks character
            battle.playTurn(new ActionCommand(enemy, character));

            newHp = character.getStats().getCurrentHitpoints();

            saveCharacter(character);
        }

//        damage = oldHp - newHp;
        message = attacker +  " hits " + damage + " on " + defender;

        turnCounter++;

        // Update character and enemy

        return message;
    }

    public Character saveCharacter(Character c) {
        return characterRepository.save(c);
    }

    public Enemy saveEnemy(Enemy e) {
        return enemyRepository.save(e);
    }

}