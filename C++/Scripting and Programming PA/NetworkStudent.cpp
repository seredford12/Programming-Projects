#include <iostream>
#include "NetworkStudent.h"
using std::cout;


NetworkStudent::NetworkStudent() :Student()
{
	Program = NETWORK;
}

NetworkStudent::NetworkStudent(string StudentID, string FirstName, string LastName, string EmailAddress, int Age, int DaysInCourse[], Degree pro)
	: Student(StudentID, FirstName, LastName, EmailAddress, Age, DaysInCourse)
{
	Program = pro;
}

Degree NetworkStudent::getDegreeProgram()
{
	return Program;
}

void NetworkStudent::print()
{
	cout << getID() << "\t First Name: " << getFirst() << "\t Last Name: " << getLast() << "\t Email Address: " << getEmail() << "\t Age: " << getA() << "\t Days In Course: {" << getDaysInCourse()[0] << ", " << getDaysInCourse()[1] << ", " << getDaysInCourse()[2] << "}" << "\t Degree: NETWORK" << "\n";
}

NetworkStudent::~NetworkStudent()
{

}
