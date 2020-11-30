DROP TABLE IF EXISTS contrat_statut, contrat_version, contrat, societe, adresse_mail, personne;

CREATE TABLE personne
(
    id        BIGINT      NOT NULL
        CONSTRAINT personne_pkey PRIMARY KEY,
    nom       VARCHAR(50) NOT NULL,
    prenom    VARCHAR(50) NOT NULL,
    avocat    BOOLEAN     NOT NULL,
    president BOOLEAN     NOT NULL
);

CREATE TABLE adresse_mail
(
    id             BIGINT      NOT NULL
        CONSTRAINT adresse_mail_pkey PRIMARY KEY,
    libelle        VARCHAR(50) NOT NULL,
    fk_personne_id BIGINT      NOT NULL
        CONSTRAINT adresse_mail_personne_fkey REFERENCES personne
);

CREATE TABLE societe
(
    id                       BIGINT      NOT NULL
        CONSTRAINT societe_pkey PRIMARY KEY,
    nom                      VARCHAR(50) NOT NULL,
    numero                   VARCHAR(5)  NOT NULL,
    fk_personne_avocat_id    BIGINT      NOT NULL
        CONSTRAINT societe_avocat_fkey REFERENCES personne,
    fk_personne_president_id BIGINT      NOT NULL
        CONSTRAINT societe_president_fkey REFERENCES personne
);

CREATE TABLE contrat
(
    id            BIGINT      NOT NULL
        CONSTRAINT contrat_pkey PRIMARY KEY,
    nom           VARCHAR(50) NOT NULL,
    fk_societe_id BIGINT      NOT NULL
        CONSTRAINT contrat_societe_fkey REFERENCES societe
);

CREATE TABLE contrat_version
(
    id             BIGINT      NOT NULL
        CONSTRAINT contrat_version_pkey PRIMARY KEY,
    nom            VARCHAR(50) NOT NULL,
    numero_version INT         NOT NULL,
    actif          BOOLEAN     NOT NULL,
    fk_contrat_id  BIGINT      NOT NULL
        CONSTRAINT contrat_version_contrat_fkey REFERENCES contrat
);

CREATE TABLE contrat_statut
(
    id            BIGINT      NOT NULL
        CONSTRAINT contrat_statut_pkey PRIMARY KEY,
    statut        VARCHAR(50) NOT NULL,
    actif         BOOLEAN     NOT NULL,
    fk_contrat_id BIGINT      NOT NULL
        CONSTRAINT contrat_statut_contrat_fkey REFERENCES contrat
);
