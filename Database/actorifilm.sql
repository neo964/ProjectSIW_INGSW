-- Table: public."ActorInFilm"

-- DROP TABLE public."ActorInFilm";

CREATE TABLE public."ActorInFilm"
(
    "Actor" text COLLATE pg_catalog."default" NOT NULL,
    "FilmID" integer NOT NULL,
    CONSTRAINT "ActorInFilm_pkey" PRIMARY KEY ("Actor", "FilmID"),
    CONSTRAINT "Film" FOREIGN KEY ("FilmID")
        REFERENCES public."Film" ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."ActorInFilm"
    OWNER to postgres;