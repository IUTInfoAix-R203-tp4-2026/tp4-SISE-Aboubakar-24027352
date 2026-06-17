package fr.univ_amu.iut.exercice5;

/// Exercice 5 - Introduce Parameter Object.
///
/// La méthode [#envoyer] prend **7 paramètres**. C'est le smell **Long Parameter
/// List** dans toute sa gloire :
///
/// - L'appel est illisible : `envoyer("a@b.c", "c@d.e", "Sujet", "Corps", true,
///   3, null)` - quel booléen ? quel numéro ?
/// - Les paramètres sont **couplés** : destinataire, expéditeur et corps vont
///   toujours ensemble ; les dissocier n'a aucun sens métier
/// - Ajouter un 8e paramètre (ex: pièce jointe) impose de modifier **tous les
///   appelants**
///
/// Refactoring attendu : **Introduce Parameter Object**. Créer une classe
/// `MessageEmail` (un `record` est parfait) qui regroupe les 7 paramètres. La
/// méthode devient `envoyer(MessageEmail)` : un seul paramètre, un contrat
/// explicite, et ajouter un champ ne casse plus les appelants.
public class ServiceNotification {

  /// Formate et envoie (simulation) un email.
  ///
  /// @return la représentation textuelle du mail envoyé, pour vérification en test
  public String envoyer(MessageEmail message) {
    StringBuilder sb = new StringBuilder();

    if (message.important()) {
      sb.append("[IMPORTANT] ");
    }

    sb.append("[P").append(message.priorite()).append("] ");
    sb.append("De: ")
        .append(message.expediteur())
        .append(", A: ")
        .append(message.destinataire())
        .append("\n");

    sb.append("Sujet: ").append(message.sujet()).append("\n");
    sb.append("Corps: ").append(message.corps());

    if (message.piecesJointes() != null && message.piecesJointes().length > 0) {

      sb.append("\nPieces jointes: ");

      for (int i = 0; i < message.piecesJointes().length; i++) {
        if (i > 0) {
          sb.append(", ");
        }
        sb.append(message.piecesJointes()[i]);
      }
    }
    return sb.toString();
  }

  public String envoyer(
      String destinataire,
      String expediteur,
      String sujet,
      String corps,
      boolean important,
      int priorite,
      String[] piecesJointes) {

    return envoyer(
        new MessageEmail(
            destinataire, expediteur, sujet, corps, important, priorite, piecesJointes));
  }
}
