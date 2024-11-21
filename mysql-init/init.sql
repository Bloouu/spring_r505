CREATE TABLE Utilisateur (
                             ID_USER INT AUTO_INCREMENT PRIMARY KEY,
                             login VARCHAR(50) NOT NULL UNIQUE,
                             mot_de_passe VARCHAR(255) NOT NULL,
                             role ENUM('publisher', 'moderator') NOT NULL
);

CREATE TABLE Article (
                         ID_ARTICLE INT AUTO_INCREMENT PRIMARY KEY,
                         date_publication DATETIME NOT NULL,
                         id_user INT NOT NULL,
                         contenu TEXT NOT NULL,
                         FOREIGN KEY (id_user) REFERENCES Utilisateur(ID_USER) ON DELETE CASCADE
);

CREATE TABLE Likes (
                       id_utilisateur INT NOT NULL,
                       id_article INT NOT NULL,
                       `like` BOOLEAN NOT NULL,
                       PRIMARY KEY (id_utilisateur, id_article),
                       FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(ID_USER) ON DELETE CASCADE,
                       FOREIGN KEY (id_article) REFERENCES Article(ID_ARTICLE) ON DELETE CASCADE
);