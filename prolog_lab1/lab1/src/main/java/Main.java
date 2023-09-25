import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;

import java.util.ArrayList;


public class Main {


    public static void main(String[] args) {
        RequestParser parser = new RequestParser();
        PrologRequest prologRequest = new PrologRequest();
        String file = "src/test.pl";
        while (true) {
            String[] input = parser.read();
            Integer match = parser.getMatch(input[3]);
            Integer rank = parser.getRank(input[9]);
            String position = parser.getPosition(input[7]);
            Query q = new Query("consult", new Term[]{new Atom(file)});
            q.hasSolution();

            ArrayList<String> player = prologRequest.getPlayersSamePosition(position);
            prologRequest.getOneMatch(position, rank, player);
            if (match == 2) {
                prologRequest.getPairMatch(rank, player);
            }


            System.out.println();

        }

    }
}
