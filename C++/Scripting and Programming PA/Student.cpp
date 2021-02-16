#include <iostream>
#include <iomanip>
#include "Student.h"
using std::cout;
using std::left;
using std::setw;
using std::end;

Student::Student()
{
	this->StudentID = "";
	this->FirstName = "";
	this->LastName = "";
	this->EmailAddress = "";
	this->Age = 0;
	for (int i = 0; i < CourseArraySize; i++) this->DaysInCourse[i] = 0;
}

Student::Student(string ID, string First, string Last, string Email, int A, int DaysInCourse[])
{
	this->StudentID = ID;
	this->FirstName = First;
	this->LastName = Last;
	this->EmailAddress = Email;
	this->Age = A;
	for (int i = 0; i < CourseArraySize; i++) this->DaysInCourse[i] = DaysInCourse[i];
}

string Student::getID()
{
	return StudentID;
}

string Student::getFirst()
{
	return FirstName;
}

string Student::getLast()
{
	return LastName;
}

string Student::getEmail()
{
	return EmailAddress;
}

int Student::getA()
{
	return Age;
}

int * Student::getDaysInCourse()
{
	return DaysInCourse;
}

void Student::setID(string ID)
{
	this->StudentID = ID;
}

void Student::setFirst(string First)
{
	this->FirstName = First;
}

void Student::setLast(string Last)
{
	this->LastName = Last;
}

void Student::setEmail(string Email)
{
	this->EmailAddress = Email;
}

void Student::setA(int A)
{
	this->Age = A;
}

void Student::setDaysInCourse(int DaysInCourse[])
{
	for (int i = 0; i < CourseArraySize; i++) this->DaysInCourse[i] = DaysInCourse[i];
}

Student::~Student()
{

}