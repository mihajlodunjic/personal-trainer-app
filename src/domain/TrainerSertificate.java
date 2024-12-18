/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author pc
 */
public class TrainerSertificate {
    private Trainer trainer;
    private Sertificate sertificate;

    public TrainerSertificate() {
    }

    public TrainerSertificate(Trainer trainer, Sertificate sertificate) {
        this.trainer = trainer;
        this.sertificate = sertificate;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Sertificate getSertificate() {
        return sertificate;
    }

    public void setSertificate(Sertificate sertificate) {
        this.sertificate = sertificate;
    }
    
}
