--SET schema 'spellingBee'
--ALTER ROLE postgres SET search_path TO spellingBee;
--SET SCHEMA to "spellingBee";
--DROP TABLE "spellingBee"."User";
CREATE TABLE User
(
  UserId numeric,
  UserName text,
  Role text
);

CREATE TABLE ContentMaster
(
  WordId numeric,
  Word text,
  WordLevel text,
  Status text,
  RejectedReason text,
  UpdatedDate date,
  UpdatedBy numeric
);
CREATE TABLE WordPronunciation
(
  PronunciationId numeric,
  WordId numeric,
  PronunciationAudio text,
  UserId numeric,
  CreatedDate date,
  CreatedBy numeric
);
