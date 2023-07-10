# Makefile
SERVICE = gdm.service
PROGNAME = application-0.0.1-SNAPSHOT.jar
SOURCE = ./application/target
DEST = /opt/gdmonitornlp

default: build

all: pull stop build copy start

stop:
	sudo systemctl stop $(SERVICE)

start:
	sudo systemctl start $(SERVICE)

build:
	mvn clean package

run:
	java -jar $(SOURCE)/$(PROGNAME)

install:
	mvn install

clean:
	mvn clean

test:
	mvn test

pull:
	git pull origin master

copy:
	cp $(SOURCE)/$(PROGNAME) $(DEST)
