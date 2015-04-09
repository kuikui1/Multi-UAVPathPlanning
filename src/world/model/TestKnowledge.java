/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world.model;

import com.hp.hpl.jena.ontology.OntModel;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.LinkedList;
import world.model.shape.Point;

/**
 *
 * @author boluo
 */
public class TestKnowledge {

    public static void main(String args[]) {
        KnowledgeInterface kb1 = new WorldKnowledge();
        OntologyBasedKnowledge kb2 = new OntologyBasedKnowledge();
        boolean result;

        Polygon polygon1 = new Polygon();
        polygon1.addPoint(0, 1);
        polygon1.addPoint(2, 4);
        polygon1.addPoint(3, 6);
        Obstacle obs1 = new Obstacle(polygon1, 0);
        Polygon polygon2 = new Polygon();
        polygon2.addPoint(0, 2);
        polygon2.addPoint(2, 3);
        polygon2.addPoint(3, 5);
        Obstacle obs2 = new Obstacle(polygon2, 0);

        kb2.addObstacle(obs1);
        result = kb2.containsObstacle(obs2);
        kb2.addObstacle(obs2);
        result = kb2.containsObstacle(obs2);
        ArrayList<Obstacle> obstacles = kb2.getObstacles();
        kb2.removeObstacle(obs1);
        obstacles = kb2.getObstacles();
        obstacles.clear();;
        obstacles.add(obs1);

        obstacles.add(obs2);
        kb2.setObstacles(obstacles);
        obstacles = kb2.getObstacles();
        result = kb2.containsObstacle(obs2);

        float[] coord1 = new float[2];
        coord1[0] = 1;
        Threat threat1 = new Threat(0, coord1, 0);
        threat1.setSpeed(2);
        threat1.setTarget_type(0);
        threat1.setThreat_cap("aaa");
        threat1.setThreat_range(2);
        float[] coord2 = new float[2];
        coord1[0] = 2;
        Threat threat2 = new Threat(0, coord2, 0);
        threat2.setSpeed(3);
        threat2.setTarget_type(3);
        threat2.setThreat_cap("bbb");
        threat2.setThreat_range(3);

        kb2.addThreat(threat1);
        result=kb2.containsThreat(threat2);
        kb2.addThreat(threat2);
        result=kb2.containsThreat(threat2);
        ArrayList<Threat> threats = kb2.getThreats();
        kb2.removeThreat(threat1);

        threats = kb2.getThreats();
        obstacles = kb2.getObstacles();
        threats.clear();
        threats.add(threat1);
        threats.add(threat2);
        kb2.setThreats(threats);
        threats = kb2.getThreats();

        LinkedList<Point> path1 = new LinkedList<Point>();
        Point point1 = new Point(1, 1, 0);
        point1.setDecision_time_step(0);
        point1.setExptected_time_step(0);
        Point point2 = new Point(1, 2, 0);
        point2.setDecision_time_step(0);
        point2.setExptected_time_step(1);
        path1.add(point1);
        path1.add(point2);
        Conflict conflict1 = new Conflict(1, path1, 0, 2);
        conflict1.setUav_index(0);
        LinkedList<Point> path2 = new LinkedList<Point>();
        Point point3 = new Point(2, 1, 0);
        point3.setDecision_time_step(0);
        point3.setExptected_time_step(0);
        Point point4 = new Point(2, 2, 0);
        point4.setDecision_time_step(0);
        point4.setExptected_time_step(1);
        path2.add(point3);
        path2.add(point4);
        Conflict conflict2 = new Conflict(1, path2, 0, 2);
        conflict2.setUav_index(1);
        kb2.addConflict(conflict1);
        kb2.addConflict(conflict2);
        ArrayList<Conflict> conflicts = kb2.getConflicts();
        kb2.removeConflict(conflict1);
        conflicts = kb2.getConflicts();
        obstacles = kb2.getObstacles();
        threats = kb2.getThreats();
        System.out.println();
//        ArrayList<Conflict> conflicts = kb.getConflicts();

    }
}
