package pittest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConferenceClassTest {

    private Conference conference;


    @Test
    public void calculatePriceForOneStudent() {
        conference=new Conference(5);
        double expected = 0.0;
        Person p = new Person("Petko","Petkovski",Role.STUDENT,25);
        conference.addAttendeeToConference(p);
        assertEquals(expected,conference.calculateTotalPricePaid(),0);
    }

    @Test
    public void calculatePriceForOneOrganizer() {
        double expected = 0.0;
        conference=new Conference(5);
        Person p = new Person("Petko","Petkovski",Role.ORGANIZER,53);
        conference.addAttendeeToConference(p);
        assertEquals(expected,conference.calculateTotalPricePaid(),0);

    }



    @Test
    public void calculatePriceForOneOrdinaryPerson() {
        conference=new Conference(5);
        Person p = new Person("Petko","Petkovski",Role.OTHER,35);
        conference.addAttendeeToConference(p);
        assertEquals(Conference.TICKET_PRICE,conference.calculateTotalPricePaid(),0);
    }

    @Test
    public void calculatePriceForOneFacultyEmployee() {
        conference=new Conference(5);
        Person p = new Person("Petko","Petkovski",Role.FACULTY_EMPLOYEE,45);
        p.setSurname("Kostov");
        conference.addAttendeeToConference(p);
        assertEquals((1-Conference.FACULTY_EMPLOYEE_DISCOUNT)*Conference.TICKET_PRICE
                ,conference.calculateTotalPricePaid(),0);
    }

    @Test
    public void CalculatePriceForOneAffiliate() {
        double expected = (1-Conference.AFFILIATE_DISCOUNT)*Conference.TICKET_PRICE;
        conference=new Conference(5);
        Person p = new Person("Petko","Petkovski",Role.AFFILIATE,42);
        conference.addAttendeeToConference(p);
        assertEquals(expected,conference.calculateTotalPricePaid(),0);
    }

    @Test
    public void doubledCapacity () {
        conference = new Conference(4);
        Person p1 = new Person("Mitko","Mitkovski",Role.STUDENT,25);
        Person p2 = new Person("Mitko","Spasevski",Role.ORGANIZER,25);
        Person p3 = new Person("Mile","Milevski",Role.OTHER,25);
        Person p4 = new Person("Spase","Trajkovski", Role.AFFILIATE,47);
        Person p5 = new Person("Marko","Cepenkov",Role.OTHER,34);
        conference.addAttendeeToConference(p1);
        conference.addAttendeeToConference(p2);
        conference.addAttendeeToConference(p3);
        conference.addAttendeeToConference(p4);
        conference.addAttendeeToConference(p5);
        assertEquals(conference.getCapacity(),conference.getAttendees().size()*2-2);

    }


    @Test
    public void CantDoubleCapacity() {
        conference = new Conference(5050);
        for(int i=0;i<5050;i++) {
            Person p = new Person("Name"+i,"Surname"+i,Role.values()[i%5],i%60);
            conference.addAttendeeToConference(p);
        }
        Person p1 = new Person("Mitko","Mitkovski",Role.STUDENT,25);
        assertFalse(conference.addAttendeeToConference(p1));

    }

    @Test
    public void DoubleCapacityBorderValue() {
        conference = new Conference(5000);
        for(int i=0;i<5000;i++) {
            Person p = new Person("Name"+i,"Surname"+i,Role.values()[i%5],i%60);
            conference.addAttendeeToConference(p);
        }
        Person p1 = new Person("Mitko","Mitkovski",Role.STUDENT,25);
        assertTrue(conference.addAttendeeToConference(p1));


    }


}
