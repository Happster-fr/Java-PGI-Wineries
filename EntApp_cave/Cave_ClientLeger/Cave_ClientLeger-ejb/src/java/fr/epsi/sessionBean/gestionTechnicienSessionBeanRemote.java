/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.sessionBean;

import fr.epsi.cave.ejbentity.Technicien;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Mikhael
 */
@Remote
public interface gestionTechnicienSessionBeanRemote {
    public List<Technicien> getTechncienDispo(Date date);

}
