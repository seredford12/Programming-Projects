#pragma once
#include <string>
#include <iostream>
#include "Student.h"
#include "NetworkStudent.h"
#include "SoftwareStudent.h"
#include "SecurityStudent.h"


class Roster
{
private:
	int LastIndex;
	int Capacity;
	Student** ClassRosterArray;

public:
	Roster();
	Roster(int Capacity);

	Student* getStudentAt(int Index);
	void parseThenAdd(string datarow);
	void add(string StudentID, string FirstName, string LastName, string EmailAddress, int Age, int DaysInCourse1, int DaysInCourse2, int DaysInCourse3, Degree Program);
	void remove(string StudentID);
	void printAll();
	void printAverageDaysInCourse(string StudentID);
	void printInvalidEmails();
	void printByDegreeProgram(Degree DegreeProgram);
	~Roster();
};