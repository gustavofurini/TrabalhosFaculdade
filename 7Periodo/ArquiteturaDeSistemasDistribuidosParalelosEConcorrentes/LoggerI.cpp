#include "LoggerI.h"
#include <iostream>

using namespace std;
using namespace CORBA;

extern ORB_var orb;
bool verbose_;
// Implementation skeleton constructor
Logger_i::Logger_i ()
{
  verbose_ = true;
}

// Implementation skeleton destructor
Logger_i::~Logger_i ()
{
}

::CORBA::Boolean Logger_i::verbose ()
{
  return true;// Add your implementation here
}

void Logger_i::verbose (
  ::CORBA::Boolean verbose)
{
 cout << "Verbose:" << verbose << endl;
 verbose_ = verbose;
}

void Logger_i::log (
  ::Severity severidade,
  const std::string endereco,
  ::CORBA::UShort pid,
  ::CORBA::Long hora,
  const std::string msg)
{
  if (verbose_) {
    cout << " Severidade -> " << severidade << " Endereco -> " << endereco << " pid -> " << pid << " hora -> " << hora << " mensagem -> " << msg << endl;
  }
}

void Logger_i::shutdown ()
{
 orb->shutdown();
}