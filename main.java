import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Transition {
    private String initialState;
    private String finalState;
    private String value;


    public Transition(String initialState, String finalState, String value) {
        this.initialState=initialState;
        this.finalState=finalState;
        this.value=value;
    }

    public String getInitialState() {
        return initialState;
    }

    public String getFinalState() {
        return finalState;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "initialState='" + initialState + '\'' +
                ", finalState='" + finalState + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

class FA {
    private List<String> states = new ArrayList<>();
    private Set<String> alphabet = new HashSet<>();
    private List<Transition> transitions = new ArrayList<>();
    private String initialState;
    private List<String> finalState = new ArrayList<>();

    public FA() {
    }

    public FA(List<String> states, Set<String> alphabet, List<Transition> transitions, String initialState, List<String> finalSates) {
        this.states=states;
        this.alphabet=alphabet;
        this.transitions=transitions;
        this.initialState=initialState;
        this.finalState=finalSates;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public void setAlphabet(Set<String> alphabet) {
        this.alphabet = alphabet;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    public void setFinalState(List<String> finalState) {
        this.finalState = finalState;
    }


    public List<String> getStates() {
        return states;
    }

    public Set<String> getAlphabet() {
        return alphabet;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public String getInitialState() {
        return initialState;
    }

    public List<String> getFinalState() {
        return finalState;
    }
}


public class main {
    public static void main(String[] args) {
        FA fa=new FA();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/FA.txt"))) {
            String[] line;

            //states
            List<String> states=new ArrayList<>();
            line = bufferedReader.readLine().split(" ");
            Collections.addAll(states, line);
            fa.setStates(states);

            //types of states
            List<String> types_of_states = new ArrayList<>();
            line = bufferedReader.readLine().split(" ");
            Collections.addAll(types_of_states,line);

            //initial state and final states
            List<String> finalStates=new ArrayList<>();
            for (int i = 0; i < states.size(); i++) {
                if (types_of_states.get(i).equals("i")) {
                    fa.setInitialState(states.get(i));
                }
                if (types_of_states.get(i).equals("f")) {
                    finalStates.add(states.get(i));
                }
            }
            fa.setFinalState(finalStates);

            //transitions
            List<Transition> transitions=new ArrayList<>();
            Set<String> alphabet=new HashSet<>();
            int nr = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < nr; i++) {
                line = bufferedReader.readLine().split(" ");
                transitions.add(new Transition(line[0], line[1], line[2]));
                alphabet.add(line[2]);
            }
            fa.setAlphabet(alphabet);
            fa.setTransitions(transitions);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("1. Set of states.");
        System.out.println("2. Alphabet. ");
        System.out.println("3. Transitions. ");
        System.out.println("4. Initial state. ");
        System.out.println("5. Set of final states. ");
        System.out.println("0. Break. ");

        while (true) {
            System.out.print("Give a command: ");
            System.out.println();
            int ok = new Scanner(System.in).nextInt();

            if (ok == 1) {
                System.out.println("Set of states: ");
                for (String state : fa.getStates()) {
                    System.out.print(state + " ");
                }
                System.out.println("\n");
            }
            else if (ok == 2) {
                System.out.println("Alphabet:");
                for (String a : fa.getAlphabet()) {
                    System.out.print(a + " ");
                }
                System.out.println("\n");
            }
            else if (ok == 3) {
                System.out.println("Transitions:");
                for (Transition t : fa.getTransitions()) {
                    System.out.println(t.getInitialState() + " --" + t.getValue() + "--> " + t.getFinalState());
                }
                System.out.println("\n");
            }
            else if (ok == 4) {
                System.out.println("Initial state:");
                System.out.println(fa.getInitialState());
                System.out.println("\n");
            }
            else if (ok == 5) {
                System.out.println("Set of final states");
                for (String state : fa.getFinalState()) {
                    System.out.print(state + " ");
                }
                System.out.println();
            }
            else if (ok == 0) {
                break;
            }
            else {
                System.out.print("Error");
            }
        }

    }
}
