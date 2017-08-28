# MOPAC INSTRUCTIONS

## INSTALATION GUIDE

This is a quick tutorial on how to install MOPAC on Ubuntu OS 2016.04 LTS.
The following packages are required:
```
$ sudo apt-get install scons openbabel libiomp5
```

**[Download](http://openmopac.net/downloads.html) MOPAC latest version (2016).**

**Create "/opt/mopac/" and copy MOPAC2016.exe to it**
```
$ sudo mkdir /opt/mopac
$ sudo cp MOPAC2016.exe /opt/mopac/
```
**Create a link to MOPAC (making possible to run simply as 'mopac')**
```
$ sudo ln -sf /opt/mopac/MOPAC2016.exe /usr/local/bin/mopac
```
**Give some permissions**
```
$ sudo chmod o+rwx /opt/mopac/MOPAC2016.exe
$ sudo chmod u+rwx /opt/mopac/MOPAC2016.exe
```
**Fix the libiomp5 missing file**
```
$ sudo ln -s /usr/lib/libiomp5.so.5 /usr/lib/libiomp5.so
```
**Insert license key:**
```
$ sudo mopac "xxxxxxxxxxxxxxxx"
```
Replacing the "xxxxxxxxxxxxxxxx" by the license you got from [OpenMopac](http://openmopac.net/downloads.html).

## RUN MOPAC for new molecules

Clone or [download](https://github.com/lvpp/sigma/archive/master.zip) this repository to your machine.

Uncompress the contents of the **sigma-master.zip** file.

Either construct a **.mol** file for the molecule you need or simply choose one already available in the **mol** folder.

Copy all the **.mol** files you want to process to the **MOPAC** folder.
In a terminal, access the **MOPAC** folder and run **scons** as follows:
```
cd sigma-master/MOPAC
scons
```

By doing this, all the **.mol** files in the folder will be processed and the results will be in **.cos** files.
MOPAC is usually very fast, but can take some minutes to process large molecules.
