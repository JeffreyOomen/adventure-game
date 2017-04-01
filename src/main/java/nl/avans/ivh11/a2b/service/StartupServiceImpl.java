package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.battle.SpecialAttack;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilder;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilderDirector;
import nl.avans.ivh11.a2b.domain.util.Media;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * StartupServiceImpl
 * makes sure all necessary items are persisted in the database before the game is started.
 */
@Component
public class StartupServiceImpl implements StartupService {

    private MediaService mediaService;
    private EnemyRepository enemyRepository;

    @Autowired
    public StartupServiceImpl(MediaService mediaService, EnemyRepository enemyRepository) {
        this.mediaService = mediaService;
        this.enemyRepository = enemyRepository;
    }

    @Override
    public void initializeGame() {
        // Important order, first all images are needed before initializing enemies
        this.initializeImages();
        this.initializeEnemies();
    }

    /**
     *
     */
    private void initializeEnemies() {
        // Find media image

        // Initialize an enemy builder for creating enemies
        EnemyBuilderDirector enemyBuilderDirector = new EnemyBuilderDirector(new EnemyBuilder());

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemyBuilderDirector.createEnemy("Bandit", mediaService.findByName("Bandit"), "Very strong bandit that lives int the forest.", new SpecialAttack()));
        enemies.add(enemyBuilderDirector.createEnemy("Bat", mediaService.findByName("Bat"), "Nasty bat.", new NormalAttack()));
        enemies.add(enemyBuilderDirector.createEnemy("Dragon", mediaService.findByName("Dragon"), "Dragon.", new NormalAttack()));
        enemies.add(enemyBuilderDirector.createEnemy("Ghost", mediaService.findByName("Ghost"), "Scary Ghost", new NormalAttack()));
        enemies.add(enemyBuilderDirector.createEnemy("Giant", mediaService.findByName("Giant"), "Big robust Giant", new NormalAttack()));
        enemies.add(enemyBuilderDirector.createEnemy("Goblin", mediaService.findByName("Goblin"), "Little but strong Goblin", new SpecialAttack()));
        enemies.add(enemyBuilderDirector.createEnemy("Mummy", mediaService.findByName("Mummy"), "Mummy from Egypt", new NormalAttack()));
        enemies.add(enemyBuilderDirector.createEnemy("Spider", mediaService.findByName("Spider"), "Dark spider", new NormalAttack()));

        // Persist all enemies in the database
        for(Enemy e : enemies) {
            enemyRepository.save(e);
        }

    }

    /**
     * initializeImages
     * Persist all possible Media items that can be used through the game.
     */
    private void initializeImages() {
        ArrayList<Media> mediaList = new ArrayList<>();

        // Enemies
        mediaList.add(new Media("bandit", "../images/rpg-bandit.png"));
        mediaList.add(new Media("bat", "../images/rpg-bat.png"));
        mediaList.add(new Media("dragon","../images/rpg-dragon.png"));
        mediaList.add(new Media("ghost", "../images/rpg-ghost.png"));
        mediaList.add(new Media("giant", "../images/rpg-giant.png"));
        mediaList.add(new Media("goblin", "../images/rpg-goblin.png"));
        mediaList.add(new Media("mummy", "../images/rpg-mummy.png"));
        mediaList.add(new Media("spider", "../images/rpg-spider.png"));

        // Character races
        mediaList.add(new Media("dwarf", "./images/rpg-dwarf.png"));
        mediaList.add(new Media("elf", "../images/rpg-elf.png"));
        mediaList.add(new Media("warrior", "../images/rpg-warrior.png"));
        mediaList.add(new Media("troll", "../images/rpg-troll.png"));

        // Loot
        // TODO: implementeren i.p.v harcoded
        mediaList.add(new Media("potion", "../images/rpg-potion.png"));
        mediaList.add(new Media("sword", "../images/rpg-sword.png"));

        // Save all media items
        for(Media m : mediaList) {
            this.mediaService.save(m);
        }

    }

}
