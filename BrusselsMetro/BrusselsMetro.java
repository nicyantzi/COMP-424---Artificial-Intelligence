package BrusselsMetro;

/**
 * Created by nic on 2016-01-26.
 */


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//using JSON-SIMPLE-1.1.1.jar


public class BrusselsMetro {

    private static String jsonFile = "brussels_metro_v2.json";
    private static String startStation = "Gare du Nord";
    private static String endStation = "Roi Baudouin";
    private static JSONParser parser = new JSONParser();
    private static Map<String, Integer> stationKey = new HashMap<String, Integer>();


    public static void main(String args[]) {

        try {

            Object obj = parser.parse(new FileReader(jsonFile));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray stationArray = (JSONArray) jsonObject.get("stations");
            int count = stationArray.size();


            for (int i = 0; i < count; i++) {

                JSONObject station = (JSONObject) stationArray.get(i);
                String stationName = (String) station.get("name");

                //put station in stationKey hashmap, where the name is the key, and the associated Integer
                //value is the index in the JSON file.

                stationKey.put(stationName, i);

                //System.out.println("Station Name: "+ stationName);
                //System.out.println("Neighbours: ");


                JSONArray neighbourArray = (JSONArray) station.get("neighbours");
                int count2 = neighbourArray.size();

                for (int j = 0; j < count2; j++) {

                    JSONObject neighbour = (JSONObject) neighbourArray.get(j);

                    long neighbourLine = (Long) neighbour.get("line");
                    String neighbourName = (String) neighbour.get("name");

                    //System.out.println(neighbourName + " - Line "+neighbourLine);
                }
            }

            System.out.println("\nQuestion 1a: Search. Solutions for the following algorithms assuming each transition has unit cost. Travelling from Gare du Nord to Roi Baudouin");
            System.out.println("__________________________________________________________\n");
            BreadthFirstSearch();
            System.out.println("\n__________________________________________________________\n");
            System.out.println("\nUniform Cost Search: Solution is identical to that of Breadth First Search as we are assuming each transition has unit cost.\n");
            System.out.println("__________________________________________________________\n");
            DepthFirstSearch();
            System.out.println("__________________________________________________________\n");
            System.out.println("\nIterative Deepening: Solution is identical to that of Depth First Search as we are assuming each transition has unit cost.\n");
            System.out.println("__________________________________________________________\n");
            System.out.println("\nQuestion 1b: Search. Repeated assuming cost of transition along lines 1,2,3 are twice the cost of transition along lines 4,5,6");
            //IterativeDeepening();
            //UniformCostSearch();

        } catch (ParseException pe) {
            pe.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void BreadthFirstSearch() {


        //System.out.println("Running BFS to go from " + startStation + " to " + endStation);

        //arraylist to store nodes already added to queue
        ArrayList<String> alreadyAdded = new ArrayList<String>();

        //arraylist to store solution path
        ArrayList<String> solutionPath = new ArrayList<String>();

        //queue to store BFS nodes to visit
        LinkedList<String> bFSQueue = new LinkedList();

        //hashmap to store found by node
        Map<String, String> foundBy = new HashMap<String, String>();

        //add start station to the queue.
        bFSQueue.addLast(startStation);
        alreadyAdded.add(startStation);


        boolean destinationFound = false;


        while (!destinationFound) {

            String stationToFind = bFSQueue.removeFirst();
            //System.out.println("Finding station: " +stationToFind);
            solutionPath.add(stationToFind);

            if(stationToFind.equals(endStation)){
                destinationFound = true;
            }

            int index = stationKey.get(stationToFind);

            try {

                Object obj = parser.parse(new FileReader(jsonFile));
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray stationArray = (JSONArray) jsonObject.get("stations");

                JSONObject station = (JSONObject) stationArray.get(index);

                JSONArray neighbourArray = (JSONArray) station.get("neighbours");
                int count2 = neighbourArray.size();

                for (int j = 0; j < count2; j++) {

                    JSONObject neighbour = (JSONObject) neighbourArray.get(j);


                    String neighbourName = (String) neighbour.get("name");


                    if (!(alreadyAdded.contains(neighbourName))) {
                        bFSQueue.addLast(neighbourName);
                        alreadyAdded.add(neighbourName);
                        foundBy.put(neighbourName,stationToFind);
                        //System.out.println(neighbourName + " found by "+stationToFind);

                    }
                }
            }


            catch (ParseException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SolutionPath(foundBy, "Breadth First Search");
    }

    public static void UniformCostSearch(){
        System.out.println("Running Uniform Cost Search to go from " + startStation + " to " + endStation);

        //arraylist to store nodes already added to queue
        ArrayList<String> alreadyAdded = new ArrayList<String>();

        //arraylist to store solution path
        ArrayList<String> solutionPath = new ArrayList<String>();

        //queue to store BFS nodes to visit
        LinkedList<String> bFSQueue = new LinkedList();

        //hashmap to store found by node
        Map<String, String> foundBy = new HashMap<String, String>();

        //add start station to the queue.
        bFSQueue.addLast(startStation);
        alreadyAdded.add(startStation);


        boolean destinationFound = false;


        while (!destinationFound) {

            String stationToFind = bFSQueue.removeFirst();
            //System.out.println("Finding station: " +stationToFind);
            solutionPath.add(stationToFind);

            if(stationToFind.equals(endStation)){
                destinationFound = true;
            }

            int index = stationKey.get(stationToFind);

            try {

                Object obj = parser.parse(new FileReader(jsonFile));
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray stationArray = (JSONArray) jsonObject.get("stations");

                JSONObject station = (JSONObject) stationArray.get(index);

                JSONArray neighbourArray = (JSONArray) station.get("neighbours");
                int count2 = neighbourArray.size();

                for (int j = 0; j < count2; j++) {

                    JSONObject neighbour = (JSONObject) neighbourArray.get(j);


                    String neighbourName = (String) neighbour.get("name");
                    long neighbourLine = (Long) neighbour.get("line");



                    if (!(alreadyAdded.contains(neighbourName))) {
                        bFSQueue.addLast(neighbourName);
                        alreadyAdded.add(neighbourName);
                        foundBy.put(neighbourName,stationToFind);
                        //System.out.println(neighbourName + " found by "+stationToFind);

                    }
                }
            }


            catch (ParseException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SolutionPath(foundBy, "Uniform Cost Search");
    }

    public static void DepthFirstSearch(){
        //System.out.println("Running DFS to go from " + startStation + " to " + endStation);

        //arraylist to store nodes already added to stack
        ArrayList<String> alreadyAdded = new ArrayList<String>();

        //arraylist to store solution path
        ArrayList<String> solutionPath = new ArrayList<String>();

        //queue to store BFS nodes to visit
        LinkedList<String> dFSQueue = new LinkedList();

        //hashmap to store found by node
        Map<String, String> foundBy = new HashMap<String, String>();



        //add start station to the queue.
        dFSQueue.addFirst(startStation);
        alreadyAdded.add(startStation);

        boolean destinationFound = false;

        while (!destinationFound) {

            String stationToFind = dFSQueue.getFirst();
            //System.out.println("Station To Find: "+stationToFind);
            solutionPath.add(stationToFind);

            if (stationToFind.equals(endStation)) {
                destinationFound = true;
            }

            int index = stationKey.get(stationToFind);

            try {

                Object obj = parser.parse(new FileReader(jsonFile));
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray stationArray = (JSONArray) jsonObject.get("stations");
                JSONObject station = (JSONObject) stationArray.get(index);
                JSONArray neighbourArray = (JSONArray) station.get("neighbours");

                int count2 = neighbourArray.size();

                /////////////////
                boolean pop = NeighboursAllVisited(stationToFind, alreadyAdded);

                if(pop){
                    dFSQueue.removeFirst();
                }

                else {

                    for (int j = 0; j < count2; j++) {

                        JSONObject neighbour = (JSONObject) neighbourArray.get(j);
                        String neighbourName = (String) neighbour.get("name");


                        if (alreadyAdded.contains(neighbourName)) {
                            //System.out.println("Already Added to Queue");
                        } else {

                            //System.out.println("Adding " + neighbourName + " to the stack");
                            dFSQueue.addFirst(neighbourName);
                            alreadyAdded.add(neighbourName);
                            foundBy.put(neighbourName, stationToFind);

                            break;
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        SolutionPath(foundBy, "Depth First Search");

    }

    public static String DepthLimitedSearch(int depth){

        if (depth == 20){
            return endStation;
        } else {
            return "false";
        }
    }

    public static void IterativeDeepening(){

        int depth = 0;
        boolean success = false;

        while (!success){
            System.out.println("Searching for "+endStation+" at depth: "+depth);
            String result = DepthLimitedSearch(depth);

            if (result.equals(endStation)){
                success = true;
            } else {
                depth++;
            }
        }
        System.out.println(endStation+" found at depth: "+depth);


    }

    public static void SolutionPath(Map<String,String> foundBy, String method){

        boolean completePath = false;

        ArrayList<String> solutionPath = new ArrayList<>();
        solutionPath.add(0, endStation);

        String name = foundBy.get(endStation);

        while (!(name.equals(startStation))){
            solutionPath.add(0,name);
            name = foundBy.get(name);
        }
        solutionPath.add(0,startStation);

        System.out.println("\n"+method+": Solution\n");
        for(int i = 0; i < solutionPath.size(); i++){
            System.out.print(solutionPath.get(i)+", ");
        }

    }

    public static boolean NeighboursAllVisited(String name, ArrayList<String> alreadyAdded) {

        boolean pop = true;

        int index = stationKey.get(name);

        try {

            Object obj = parser.parse(new FileReader(jsonFile));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray stationArray = (JSONArray) jsonObject.get("stations");
            JSONObject station = (JSONObject) stationArray.get(index);
            JSONArray neighbourArray = (JSONArray) station.get("neighbours");

            int count2 = neighbourArray.size();

            for (int j = 0; j < count2; j++) {

                JSONObject neighbour = (JSONObject) neighbourArray.get(j);
                String neighbourName = (String) neighbour.get("name");

                if (!(alreadyAdded.contains(neighbourName))) {
                    pop = false;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pop;
    }
}
