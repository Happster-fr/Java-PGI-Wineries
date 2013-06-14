/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.utils;

import fr.epsi.cave.ejbentity.Client;
import fr.epsi.cave.ejbentity.Contrat;
import fr.epsi.cave.ejbentity.Intervention;
import fr.epsi.cave.ejbentity.ListePiece;
import fr.epsi.cave.ejbentity.Piece;
import fr.epsi.enums.TypeContrat;
import fr.epsi.sessionBean.gestionPieceSessionBeanRemote;
import java.util.HashMap;

/**
 *
 * @author Mikhael
 */
public class FactureCreator {

    private Client _client;
    private Intervention _intervention;
    private Contrat _contrat;
    private HashMap<Integer, Float> piecePrix = new HashMap<Integer, Float>();
    private HashMap<Integer, String> pieceNom = new HashMap<Integer, String>();
    private gestionPieceSessionBeanRemote _gestionPieceSessionBeanRemote;
    //private static final int PRIX_INTERV = 130;
    private static HashMap<String, Float> _reductions = new HashMap() {
        {
            put(TypeContrat.ANNUEL.getTypeContratInsert(), 0.5);
        }

        {
            put(TypeContrat.HEBDOMADAIRE.getTypeContratInsert(), 0.9);
        }

        {
            put(TypeContrat.MENSUEL.getTypeContratInsert(), 0.7);
        }
    };
    private static HashMap<String, Float> _tarifIntervention = new HashMap() {
        {
            put(EnumNatureIntervention.PREVENTIVE.getNatureIntervention(), 150.0);
        }

        {
            put(EnumNatureIntervention.CURATIVE.getNatureIntervention(), 250.0);
        }
    };

    //private static final Float TVA = 19.6;
    public FactureCreator(Client client, Intervention intervention, Contrat contrat, gestionPieceSessionBeanRemote gestionPieceSessionBeanRemote) {
        _client = client;
        _intervention = intervention;
        _contrat = contrat;
        _gestionPieceSessionBeanRemote = gestionPieceSessionBeanRemote;
        recupereListePiece();
    }

    private void recupereListePiece() {
        for (Piece piece : _gestionPieceSessionBeanRemote.getAllPiece()) {
            piecePrix.put(Integer.parseInt(piece.getPieceId().toString()), piece.getPrix());
            pieceNom.put(Integer.parseInt(piece.getPieceId().toString()), piece.getNom());
        }
    }

    private float calculeTotalFacture() {
        float total = 0;
        for (ListePiece listePiece : _gestionPieceSessionBeanRemote.getListPieceForIntervention(_intervention.getInterventionId())) {
            total += piecePrix.get(listePiece.getPieceId()) * listePiece.getNombre();
        }
        double tarifIntervention = Double.parseDouble("" + _tarifIntervention.get(_intervention.getNature()));
        return (float) (total + tarifIntervention);
    }

    public String HtmlFacture() {
        float total_piece, totalSansReduc;
        String elements = "";
        String facture_html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\"> <head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /> <title>Facture</title> <style>.textarea { border: 0; font: 14px Georgia, Serif; overflow: hidden; resize: none; } table { border-collapse: collapse; } table td, table th { border: 1px solid black; padding: 5px; } #items { clear: both; width: 100%; margin: 30px 0 0 0; border: 1px solid black; } #items tr.item-row td { border: 0; vertical-align: top; } #items td.total-line { border-right: 0; text-align: right; } #items td.total-value { border-left: 0; padding: 10px; } #items td.blank { border: 0; } #terms h5 { text-transform: uppercase; font: 13px Helvetica, Sans-Serif; letter-spacing: 10px; border-bottom: 1px solid black; padding: 0 0 8px 0; margin: 0 0 8px 0; } td{ text-align: center;}</style></head> <body><div id=\"page-wrap\" style=\"width: 60%;\"> <div id=\"identity\"> <div class=\"textarea\"id=\"address\"> Dewine<br/> 34 rue de la vigne<br/> 34000 Montpellier<br/> Tél: 0102030405</div> </div><br /> <br /> <div id=\"customer\" style=\"text-align : right;\">"
                + _client.getStatut() + " - " + _client.getNom() + "<br/>"
                + _client.getAdresse() + "<br/> <br/>"
                + "Intervention n°" + _intervention.getInterventionId().toString() + "</div> <br/><br/> <table id=\"items\"> <tr><th>Article</th> <th>Prix unitaire</th> <th>Quantité</th> <th>Prix total (en €)</th></tr>";

        for (ListePiece listePiece : _gestionPieceSessionBeanRemote.getListPieceForIntervention(_intervention.getInterventionId())) {
            total_piece = piecePrix.get(listePiece.getPieceId()) * listePiece.getNombre();
            elements = elements + "<tr class=\"item-row\"><td class=\"item-name\"><div class=\"textarea\" >" + pieceNom.get(listePiece.getPieceId()) + "</div></td> <td>"
                    + piecePrix.get(listePiece.getPieceId()) + "</td> <td>"
                    + listePiece.getNombre() + "</td> <td>"
                    + total_piece + "</td></tr> ";
        }

        totalSansReduc = calculeTotalFacture();
        facture_html += elements + "<tr class=\"item-row\"><td class=\"item-name\"><div class=\"textarea\" >Frais de maintenance " + _intervention.getNature() + "</div></td><td></td><td></td> <td>" + _tarifIntervention.get(_intervention.getNature()) + "</td>";

        if (!_contrat.getType().equals("") && _client.getDateFinContrat().compareTo(_intervention.getDate()) > 0) {
            facture_html += "<tr><td></td><td> </td> <td class=\"total-line\">Total sans réduction</td> <td class=\"total-value\"><div id=\"total\">" + totalSansReduc + "</div></td></tr>";
            //calcul du total avec réduction
            Double reductionContrat = Double.parseDouble("" + _reductions.get(_contrat.getType()));
            float totalAvecReduc = (float) (reductionContrat * totalSansReduc);
            facture_html += "<tr><td></td><td> </td> <td class=\"total-line\">Réduction (contrat " + _contrat.getType() + ")</td> <td class=\"total-value\"><div id=\"total\">" + (totalSansReduc - totalAvecReduc) + "</div></td></tr>";
            facture_html += "<tr><td></td><td> </td> <td class=\"total-line\">Total </td> <td class=\"total-value\"><div id=\"total\">" + totalAvecReduc + "</div></td></tr></table> </div></body></html>";
        } else {
            facture_html += "<tr><td></td><td> </td> <td class=\"total-line\">Total </td> <td class=\"total-value\"><div id=\"total\">" + totalSansReduc + "</div></td></tr></table> </div></body></html>";
        }

        return facture_html;
    }
}
