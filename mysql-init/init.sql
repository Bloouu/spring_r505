CREATE TABLE utilisateur (
                             ID_USER INT AUTO_INCREMENT PRIMARY KEY,
                             login VARCHAR(50) NOT NULL UNIQUE,
                             mot_de_passe VARCHAR(255) NOT NULL,
                             role ENUM('ROLE_publisher', 'ROLE_moderator') NOT NULL
);

CREATE TABLE article (
                         ID_ARTICLE INT AUTO_INCREMENT PRIMARY KEY,
                         date_publication DATETIME NOT NULL,
                         id_user INT NOT NULL,
                         contenu TEXT NOT NULL,
                         FOREIGN KEY (id_user) REFERENCES utilisateur(ID_USER) ON DELETE CASCADE
);

CREATE TABLE likes (
                       id_likes INT AUTO_INCREMENT PRIMARY KEY,
                       id_utilisateur INT NOT NULL,
                       id_article INT NOT NULL,
                       `like` BOOLEAN NOT NULL,
                       UNIQUE (id_utilisateur, id_article),
                       FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(ID_USER) ON DELETE CASCADE,
                       FOREIGN KEY (id_article) REFERENCES article(ID_ARTICLE) ON DELETE CASCADE
);