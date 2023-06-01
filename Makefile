# Makefile
SERVICE = gdmonitor.service
PROGNAME = application-0.0.1-SNAPSHOT.jar
SOURCE = ./application/target
DEST = /opt/gdmonitor

default: build

all: pull stop build copy start

stop:
	systemctl stop $(SERVICE)

start:
	systemctl start $(SERVICE)

build:	
	mvn clean package		

run:
	java -jar $(SOOURCE)/$(PROGNAME)

install:
	mvn install

clean:
	mvn clean

test:
	mvn test

pull:
	git pull origin master

copy:
	cp $(SOOURCE)/$(PROGNAME) $(DEST)
