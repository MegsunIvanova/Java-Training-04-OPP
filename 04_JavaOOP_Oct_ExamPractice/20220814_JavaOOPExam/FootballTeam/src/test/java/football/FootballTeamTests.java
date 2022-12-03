package football;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FootballTeamTests {

    private static final String PLAYER_NAME = "Pesho";
    private static final String TEAM_NAME = "Pesho's Team";
    private static final int VALID_VACANT_POSITIONS = 12;

    private Footballer footballer;

    private FootballTeam footballTeam;
    //
    private static final String STATISTICS_MESSAGE = "The footballer %s is in the team %s.";
    //

    @Before
    public void setUp() {
        this.footballer = new Footballer(PLAYER_NAME);
        footballTeam = new FootballTeam(TEAM_NAME, VALID_VACANT_POSITIONS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatingTeamWithNoPositions() {
        new FootballTeam("test_name", -1);
        footballTeam.addFootballer(new Footballer("NAME"));
    }

    @Test
    public void testCreatingTeamWithActualPositionsShouldSetCorrectPositionsCount() {
        int vacantPositions = footballTeam.getVacantPositions();
        assertEquals(VALID_VACANT_POSITIONS, vacantPositions);
    }

    @Test(expected = NullPointerException.class)
    public void testCreatingTeamWithShouldFail() {
        footballTeam = new FootballTeam(null, VALID_VACANT_POSITIONS);
    }

    @Test(expected = NullPointerException.class)
    public void testCreatingTeamWithEmptyNameShouldFail() {
        FootballTeam footballTeam = new FootballTeam(" ", VALID_VACANT_POSITIONS);
    }

    @Test
    public void testCreatingTeamWithNameShouldCreateTheTeam() {
        assertEquals(TEAM_NAME, footballTeam.getName());
    }

    @Test
    public void testAddPlayerShouldIncreaseTeamMembers() {
        footballTeam.addFootballer(footballer);
        assertEquals(1, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddPlayerShouldFailWhenTeamIsFull() {
        FootballTeam footballTeam = new FootballTeam(TEAM_NAME, 0);
        footballTeam.addFootballer(footballer);
    }

    @Test
    public void testRemoveFootballerShouldReduceTeamCount() {
        this.footballTeam.addFootballer(footballer);

        assertEquals(1, footballTeam.getCount());

        this.footballTeam.removeFootballer(footballer.getName());

        assertEquals(0, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFootballerShouldFailWhenNoSuchPlayer() {
        this.footballTeam.addFootballer(footballer);
        this.footballTeam.removeFootballer("Not_Added");
    }

    @Test
    public void testFootballerForSaleShouldDeactivatePlayer() {
        this.footballTeam.addFootballer(footballer);

        footballTeam.footballerForSale(footballer.getName());

        assertFalse(footballer.isActive());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testFootballerForSaleShouldFailIfPlayerIsMissing() {
        this.footballTeam.addFootballer(footballer);

        footballTeam.footballerForSale("Not_Added");

    }

}
