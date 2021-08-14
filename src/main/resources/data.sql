DROP TABLE IF EXISTS VIDEO CASCADE;
DROP TABLE IF EXISTS VIDEO_CATEGORY CASCADE;
CREATE TABLE VIDEO(id INTEGER NOT NULL AUTO_INCREMENT, file_path VARCHAR(255), title VARCHAR(255), category_id INTEGER, PRIMARY KEY (id));
CREATE TABLE VIDEO_CATEGORY (id INTEGER NOT NULL AUTO_INCREMENT, name VARCHAR(255), PRIMARY KEY (id));
CREATE TABLE VIDEO_THUMBNAIL (id INTEGER NOT NULL AUTO_INCREMENT, file_path VARCHAR(255), size INTEGER, video_id INTEGER, PRIMARY KEY (id));

ALTER TABLE VIDEO ADD CONSTRAINT FK_VIDEO_CATEGORY_ID FOREIGN KEY (category_id) REFERENCES VIDEO_CATEGORY;
ALTER TABLE VIDEO_THUMBNAIL ADD CONSTRAINT FK_VIDEO_THUMBNAIL_ID FOREIGN KEY (video_id) REFERENCES VIDEO;

INSERT INTO VIDEO_CATEGORY(id, name) VALUES (1, 'Exercise');
INSERT INTO VIDEO_CATEGORY(id, name) VALUES (2, 'Education');
INSERT INTO VIDEO_CATEGORY(id, name) VALUES (3, 'Recipe');