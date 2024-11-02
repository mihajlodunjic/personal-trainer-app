/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.Trainer;
import java.util.List;

/**
 *
 * @author pc
 */
public class TrainerRepository {
    private List<Trainer> trainers;
    public TrainerRepository(List<Trainer>trainers){
        this.trainers=trainers;
    }
    public List<Trainer> getAll(){
        return trainers;
    }
}
