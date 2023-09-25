import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PrologRequest {
    Variable X = new Variable("X");


    public void getOneMatch(String position, Integer rank, ArrayList<String> player) {
        Map<String, ArrayList<Integer>> personRank = getOpponentPair(player);
        if (personRank.keySet().isEmpty()) {
            System.out.println("В данный момент нет доступных персонажей вашего ранга. Пожалуйста, смените позицию или ранг.");
        } else {
            System.out.println("Вы выбрали позицию " + position + ". Рекомендуемые игроки: ");
        }
        for (String s : personRank.keySet()) {
            for (int i = 0; i < personRank.get(s).size(); i++) {
                if (personRank.get(s).get(i).equals(rank)) {
                    System.out.println(s);
                }
            }
        }
    }

    public void getPairMatch( Integer rank, ArrayList<String> player) {
        Map<String, ArrayList<Integer>> personRank1 = getOpponentPair(player, rank);
        ArrayList<String> res = new ArrayList<>(personRank1.keySet());
        System.out.println("Рекомендуемые напарники: ");
        ArrayList<String> command = getCommandPlayer(rank, res);
        for (String s : command) {
            System.out.println(s);
        }


    }

    public ArrayList<String> getPlayersSamePosition(String position) {
        Query q = new Query("playerPosition", new Term[]{X, new Atom(position)});
        java.util.Map<String, Term>[] solutions = q.allSolutions();
        ArrayList<String> player = new ArrayList<>();
        for (Map<String, Term> solution : solutions) {
            player.add(solution.get("X").toString().replaceAll("'", ""));
        }
        return player;
    }

    public Map<String, ArrayList<Integer>> getOpponentPair(ArrayList<String> player) {
        Map<String, ArrayList<Integer>> personRank = new HashMap<>();
        for (String s : player) {
            Query q1 = new Query("rank", new Term[]{new Atom(s), X});
            ArrayList<Integer> rank = new ArrayList<>();
            if (q1.hasSolution()) {
                for (Map<String, Term> t : q1.allSolutions()) {
//                    System.out.println(q1.hasSolution() + " " + Integer.parseInt(t.get("X").toString().replaceAll("'", "")));
                    rank.add(Integer.parseInt(t.get("X").toString().replaceAll("'", "")));
                }
            }
            if (!rank.isEmpty()) {
                personRank.put(s, rank);
            }
        }
        return personRank;
    }

    public ArrayList<String> getCommandPlayer(Integer value, ArrayList<String> res) {
        Query q2 = new Query("rank", new Term[]{X, new Atom(value.toString())});
        ArrayList<String> list = new ArrayList<>();
        if (q2.hasSolution()) {
            java.util.Map<String, Term>[] sol = q2.allSolutions();
            for (Map<String, Term> stringTermMap : sol) {
                list.add(stringTermMap.get("X").toString().replaceAll("'", ""));
            }
        }
        list.removeAll(res);
        return list;
    }

    public Map<String, ArrayList<Integer>> getOpponentPair(ArrayList<String> player, Integer value) {
        Map<String, ArrayList<Integer>> personRank = new HashMap<>();
        for (String s : player) {

            Query q1 = new Query("rank", new Term[]{new Atom(s), X});
            ArrayList<Integer> rank = new ArrayList<>();
            if (q1.hasSolution()) {
                for (Map<String, Term> t : q1.allSolutions()) {
//                    System.out.println(q1.hasSolution() + " " + Integer.parseInt(t.get("X").toString().replaceAll("'", "")));
                    if (Integer.parseInt(t.get("X").toString().replaceAll("'", "")) == value)
                        rank.add(Integer.parseInt(t.get("X").toString().replaceAll("'", "")));
                }
            }
            if (!rank.isEmpty()) {
                personRank.put(s, rank);

            }
        }
        return personRank;
    }

}
