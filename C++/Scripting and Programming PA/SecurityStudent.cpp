#include <iostream>
#include "SecurityStudent.h"
using std::cout;

SecurityStudent::SecurityStudent() : Student()
{
	Program = SECURITY;
}

SecurityStudent::SecurityStudent(string StudentID, string FirstName, string LastName, string EmailAddress, int Age, int DaysInCourse[], Degree pro)
	: Student(StudentID, FirstName, LastName, EmailAddress, Age, DaysInCourse)
{
	Program = pro;
}

Degree SecurityStudent::getDegreeProgram()
{
	return Program;
}

void SecurityStudent::print()
{
	cout << getID() << "\t First Name: " << getFirst() << "\t Last Name: " << getLast() << "\t Email Address: " << getEmail() << "\t Age: " << getA() << "\t Days In Course: {" << getDaysInCourse()[0] << ", " << getDaysInCourse()[1] << ", " << getDaysInCourse()[2] << "}" << "\t Degree: SECURITY" << "\n";
}

SecurityStudent::~SecurityStudent()
{
	
}