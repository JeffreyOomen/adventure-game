package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.character.EquipmentRepository;
import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.datastorage.usable.MediaRepository;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.battle.SpecialAttack;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.Dwarf;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilder;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilderDirector;
import nl.avans.ivh11.a2b.domain.usable.*;
import nl.avans.ivh11.a2b.domain.util.Media;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("opponentService")
@Repository
public class OpponentServiceImpl implements OpponentService
{
    private CharacterRepository characterRepository;
    private EnemyRepository enemyRepository;
    private EquipmentRepository equipmentRepository;
    private MediaRepository mediaRepository;
    private MediaService mediaService;

    /**
     * Constructor
     * @param characterRepository repository for CRUD on Character's
     * @param enemyRepository repository for CRUD on Enemy's
     * @param equipmentRepository repository for CRUD on Equipment
     */
    public OpponentServiceImpl(CharacterRepository characterRepository,
                               EnemyRepository enemyRepository,
                               EquipmentRepository equipmentRepository,
                               MediaRepository mediaRepository,
                               MediaService mediaService) {
        this.characterRepository = characterRepository;
        this.equipmentRepository = equipmentRepository;
        this.enemyRepository = enemyRepository;
        this.mediaRepository = mediaRepository;
        this.mediaService = mediaService;

    }

    /**
     * Gets the Character by id
     * @param id the id of the Character to be found
     * @return the Character with the specified id
     */
    @Transactional(readOnly = true)
    @Override
    public Character findCharacterById(long id) {
        return characterRepository.findOne(id);
    }

    /**
     * Find all enemies
     * @return a List of Enemy's
     */
    @Transactional
    @Override
    public List<Enemy> findAllEnemies() {
        return (List<Enemy>) enemyRepository.findAll();
    }

    /**
     * Gets the Enemy by id
     * @param id the id of the Enemy to be found
     * @return the Enemy with the specified id
     */
    @Transactional(readOnly = true)
    @Override
    public Enemy findEnemyById(long id) {
        return enemyRepository.findOne(id);
    }

    /**
     * Saves the state of a Character
     * @param character the Character to be saved
     */
    public void saveCharacter(Character character) {
        this.characterRepository.save(character);
    }
}
