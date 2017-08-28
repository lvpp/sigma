# MOPAC INSTRUCTIONS

## INSTALATION GUIDE

**[Download](http://openmopac.net/downloads.html) MOPAC last version.**

**Create "/opt/mopac/" and extract MOPAC2016.exe on it**
```
cd /opt
```
```
sudo mkdir mopac
```
```
sudo mv MOPAC2016.exe mopac
```
**Create a link to MOPAC (and it will be possible to access MOPAC in any local)**
```
sudo ln -sf /opt/mopac/MOPAC2016.exe /usr/local/bin/mopac
```
**Giving some permissions**
```
cd mopac
```
```
sudo chmod o+rwx MOPAC2016.exe
```
```
sudo chmod u+rwx MOPAC2016.exe
```
**Create a symbolic link with this command:**
```
sudo ln -s /usr/lib/libiomp5.so.5 /usr/lib/libiomp5.so
```
**Insert license key:**
```
sudo mopac "xxxxxxxxxxxxxxxx"
```
## RUN MOPAC
```
scons variant=POA1_1.18 POA1_1.18/MOLECULE.cos
```