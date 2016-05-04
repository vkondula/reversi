/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.project2015.tests;

import ija.project2015.boardgame.board.Board;
import ija.project2015.boardgame.board.Field;
import ija.project2015.boardgame.game.Rules;
import ija.project2015.boardgame.game.Game;
import ija.project2015.boardgame.game.Player;
import ija.project2015.reversi.ReversiRules;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test 2. ukolu.
 * @author koci
 */
public class Basic01Test {

    @Test
    public void testPlayer() {
        System.out.println("Player");
        
        Player p1 = new Player(true);
        assertTrue("Test prazdne sady kamenu.", p1.emptyPool());
        assertEquals("Test spravne textove reprezentace objektu.", "hrac:white", "hrac:" + p1);

        Player p2 = new Player(false);
        assertTrue("Test prazdne sady kamenu.", p2.emptyPool());
        assertEquals("Test spravne textove reprezentace objektu.", "hrac:black", "hrac:" + p2);
    }

    /**
     * Test pravidel inicializace.
     */
    @Test
    public void testRules() {
        System.out.println("Rules");
        int size = 8;
        
        Rules rules = new ReversiRules(size);
        assertEquals("Test velikosti hry", size, rules.getSize());
        assertEquals("Test poctu kamenu pro jednoho hrace", size*size/2, rules.numberDisks());
        
        Field f1 = rules.createField(2, 3);
        Field f2 = rules.createField(2, 3);
        Field f3 = rules.createField(4, 4);
        
        assertEquals("Test shody dvou stejnych poli.", f1, f2);
        assertNotEquals("Test neshody dvou ruznych poli.", f1, f3);       
    }

    /**
     * Test hry (vytvoření pravidel, desky a hráčů, základní tahy).
     */
    @Test
    public void testGame() {
        System.out.println("Game");
        int size = 8;
        
        ReversiRules rules = new ReversiRules(size);
        Board board = new Board(rules);
        Game game = new Game(board);
        
        Player p1 = new Player(true);
        Player p2 = new Player(false);
        
        game.addPlayer(p1);
        game.addPlayer(p2);
        assertFalse("Test neprazdne sady kamenu.", p1.emptyPool());
        assertFalse("Test neprazdne sady kamenu.", p2.emptyPool());
        assertEquals("Test, zda je aktualni hrac bily.", p1, game.currentPlayer());
        assertTrue("Test spravneho umisteni pocatecnich kamenu.", 
                game.getBoard().getField(4, 4).getDisk().isWhite());
        assertTrue("Test spravneho umisteni pocatecnich kamenu.", 
                game.getBoard().getField(5, 5).getDisk().isWhite());
        assertFalse("Test spravneho umisteni pocatecnich kamenu.", 
                game.getBoard().getField(4, 5).getDisk().isWhite());
        assertFalse("Test spravneho umisteni pocatecnich kamenu.", 
                game.getBoard().getField(5, 4).getDisk().isWhite());      
        Field f1 = game.getBoard().getField(3, 4);
        Field f2 = game.getBoard().getField(4, 6);
        System.out.println(game.getBoard());
        assertFalse("Test umisteni kamene na spatnou pozici.", game.currentPlayer().canPutDisk(f1));
        assertTrue("Test umisteni kamene na dobrou pozici.", game.currentPlayer().canPutDisk(f2));
        assertTrue("Umisteni kamene.", game.currentPlayer().putDisk(f2));
        System.out.println(game.getBoard());

        for (int i = 4; i <= 6; i++) {
            assertTrue("Test spravne barvy kamene.", 
                game.getBoard().getField(4, i).getDisk().isWhite());
        }
        
        game.nextPlayer();
        assertEquals("Test, zda je aktualni hrac cerny.", p2, game.currentPlayer());

        f2 = game.getBoard().getField(5, 6);
        assertTrue("Test umisteni kamene na dobrou pozici.", game.currentPlayer().canPutDisk(f2));
        assertTrue("Umisteni kamene.", game.currentPlayer().putDisk(f2));

        System.out.println(game.getBoard());

        for (int i = 4; i <= 6; i++) {
            assertFalse("Test spravne barvy kamene.", 
                game.getBoard().getField(5, i).getDisk().isWhite());
        }

    }
    
}
