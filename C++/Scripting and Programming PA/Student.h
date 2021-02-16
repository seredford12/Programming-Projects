#pragma once
#include <string>
#include "Degree.h"
using std::string;

class Student
{
public:
	const static int CourseArraySize = 3;

private:
	string StudentID;
	string FirstName;
	string LastName;
	string EmailAddress;
	int Age;
	int DaysInCourse[CourseArraySize];
	

public:
	Student();
	Student(string ID, string First, string Last, string Email, int A, int DaysInCourse[]);

	string getID();
	string getFirst();
	string getLast();
	string getEmail();
	int getA();
	int * getDaysInCourse();
	virtual Degree getDegreeProgram() = 0;

	void setID(string ID);
	void setFirst(string First);
	void setLast(string Last);
	void setEmail(string Email);
	void setA(int A);
	void setDaysInCourse(int DaysInCourse[]);
	

	virtual void print() = 0;

	~Student();
};