-- personne
INSERT INTO personne (id, nom, prenom, avocat, president)
VALUES (1, 'avocat1nom', 'avocat1prenom', true, false);

INSERT INTO personne (id, nom, prenom, avocat, president)
VALUES (2, 'president1nom', 'president1prenom', false, true);

INSERT INTO personne (id, nom, prenom, avocat, president)
VALUES (3, 'avocat2nom', 'avocat2prenom', true, false);

INSERT INTO personne (id, nom, prenom, avocat, president)
VALUES (4, 'president2nom', 'president2prenom', false, true);

-- adresse_mail
INSERT INTO adresse_mail (id, libelle, fk_personne_id)
VALUES (1, 'avocat1@societe1.fr', 1);

INSERT INTO adresse_mail (id, libelle, fk_personne_id)
VALUES (2, 'avocat1@soc1.com', 1);

INSERT INTO adresse_mail (id, libelle, fk_personne_id)
VALUES (3, 'avocat1@societe1.com', 1);

INSERT INTO adresse_mail (id, libelle, fk_personne_id)
VALUES (4, 'president1@societe1.fr', 2);

INSERT INTO adresse_mail (id, libelle, fk_personne_id)
VALUES (5, 'president1@soc1.com', 2);

INSERT INTO adresse_mail (id, libelle, fk_personne_id)
VALUES (6, 'president2@soc2.com', 4);

INSERT INTO adresse_mail (id, libelle, fk_personne_id)
VALUES (7, 'avocat2@societe2.com', 3);

-- societe
INSERT INTO societe (id, nom, numero, fk_personne_avocat_id, fk_personne_president_id)
VALUES (1, 'societe1', '1111', 1, 2);

INSERT INTO societe (id, nom, numero, fk_personne_avocat_id, fk_personne_president_id)
VALUES (2, 'societe2', '2222', 3, 4);

-- contrat
INSERT INTO contrat (id, nom, fk_societe_id)
VALUES (1, 'contrat1', 1);

INSERT INTO contrat (id, nom, fk_societe_id)
VALUES (2, 'contrat2', 1);

INSERT INTO contrat (id, nom, fk_societe_id)
VALUES (3, 'contrat3', 2);

-- contrat_statut
INSERT INTO contrat_statut (id, statut, actif, fk_contrat_id)
VALUES (1, 'en_cours', false, 1);

INSERT INTO contrat_statut (id, statut, actif, fk_contrat_id)
VALUES (2, 'annule', true, 1);

INSERT INTO contrat_statut (id, statut, actif, fk_contrat_id)
VALUES (3, 'en_cours', false, 2);

INSERT INTO contrat_statut (id, statut, actif, fk_contrat_id)
VALUES (4, 'ternime', true, 2);

INSERT INTO contrat_statut (id, statut, actif, fk_contrat_id)
VALUES (5, 'ternime', true, 3);

-- contrat_version
INSERT INTO contrat_version (id, nom, numero_version, actif, fk_contrat_id)
VALUES (1, 'version1', 1, false, 1);

INSERT INTO contrat_version (id, nom, numero_version, actif, fk_contrat_id)
VALUES (2, 'version2', 2, true, 1);

INSERT INTO contrat_version (id, nom, numero_version, actif, fk_contrat_id)
VALUES (3, 'version1', 1, true, 2);

INSERT INTO contrat_version (id, nom, numero_version, actif, fk_contrat_id)
VALUES (4, 'version1', 1, true, 3);
