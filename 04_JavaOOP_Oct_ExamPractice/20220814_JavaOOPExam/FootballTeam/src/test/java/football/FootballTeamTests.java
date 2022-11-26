package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FootballTeamTests {

    private static final String NAME = "TestName";
    private static final int VALID_VACANT_POSITIONS = 6;
    private static final String STATISTICS_MESSAGE = "The footballer %s is in the team %s.";

    private List<Footballer> footballersList;
    private FootballTeam footballTeam;

    @Before
    public void setUp() {
        footballersList = new ArrayList<>();
        footballTeam = new FootballTeam(NAME, VALID_VACANT_POSITIONS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsWithNegativeVacantPositions() {
        footballTeam = new FootballTeam(NAME, VALID_VACANT_POSITIONS * (-1));
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorThrowsWithNullName() {
        footballTeam = new FootballTeam(null, VALID_VACANT_POSITIONS);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorThrowsWithEmptyName() {
        FootballTeam footballTeam = new FootballTeam(" ", VALID_VACANT_POSITIONS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFootballerMoreThenVacantPositionsThrows() {
        addPlayersToFootballTeam();
        footballTeam.addFootballer(new Footballer("NAME"));
    }

    @Test
    public void testAddFootballerAdd() {
        List<Footballer> expected = addPlayersToFootballTeam();

        int actualSize = footballTeam.getCount();
        assertEquals(expected.size(), actualSize);

    }

    @Test
    public void testRemoveFootballerShouldRemove() {
        List<Footballer> footballersList = addPlayersToFootballTeam();

        String footballerName = footballersList.get(1).getName();

        footballTeam.removeFootballer(footballerName);

        int expectedSize = footballersList.size() - 1;
        int actualSize = footballTeam.getCount();
        assertEquals(expectedSize, actualSize);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFootballerShouldThrowIfFootballerMissing() {
        List<Footballer> footballersList = addPlayersToFootballTeam();
        String footballerName = footballersList.get(1).getName();
        footballTeam.removeFootballer(footballerName);
        footballTeam.removeFootballer(footballerName);
    }

    @Test
    public void testFootballerForSaleShouldReturnCorrectFootballer() {
        List<Footballer> footballerList = addPlayersToFootballTeam();
        Footballer expected = footballerList.get(footballerList.size() - 1);

        Footballer actual = footballTeam.footballerForSale(expected.getName());

        assertEquals(expected.getName(), actual.getName());
        assertFalse(actual.isActive());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testFootballerForSaleShouldThrowIfNoneMatchingNameOfFootballer() {
        List<Footballer> footballerList = addPlayersToFootballTeam();
        footballTeam.footballerForSale(NAME);
    }

    @Test
    public void testGetStatisticsShouldReturnCorrectText () {
        List<Footballer> footballerList = addPlayersToFootballTeam();
        String namesOfFootballers = footballerList.stream()
                .map(Footballer::getName)
                .collect(Collectors.joining(", "));
        String expected = String.format(STATISTICS_MESSAGE, namesOfFootballers, NAME);
        String actual = footballTeam.getStatistics();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNameShouldReturnACorrectName () {
        assertEquals(NAME, footballTeam.getName());
    }

    @Test
    public void testGetVacantPositionsShouldReturnACorrectVacantPositions () {
        assertEquals(VALID_VACANT_POSITIONS, footballTeam.getVacantPositions());
    }

    private List<Footballer> addPlayersToFootballTeam() {

        footballersList.add(new Footballer("Test_Name_01"));
        footballersList.add(new Footballer("Test_Name_02"));
        footballersList.add(new Footballer("Test_Name_03"));
        footballersList.add(new Footballer("Test_Name_04"));
        footballersList.add(new Footballer("Test_Name_05"));
        footballersList.add(new Footballer("Test_Name_06"));
        footballersList.add(new Footballer("Test_Name_07"));
        footballersList.add(new Footballer("Test_Name_08"));

        for (int i = 0; i < VALID_VACANT_POSITIONS; i++) {
            footballTeam.addFootballer(footballersList.get(i));
        }

        return footballersList.subList(0, VALID_VACANT_POSITIONS);
    }
}
