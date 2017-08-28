## MOPAC INSTRUCTIONS

# INSTALATION GUIDE

**[Download](http://openmopac.net/downloads.html) MOPAC last version.**

**Create "/opt/mopac/" and extract MOPACXXXX.exe on it**
```
cd /opt
```
```
sudo mkdir mopac
```
```
sudo mv MOPACXXXX.exe mopac
```
**Create a link to MOPAC (and it will be possible to access MOPAC in any local)**
```
sudo ln -sf /opt/mopac/MOPACXXXX.exe /usr/local/bin/mopac
```
**Giving some permissions**
```
cd mopac
```
```
sudo chmod o+rwx MOPACXXXX.exe
```
```
sudo chmod u+rwx MOPACXXXX.exe
```
**Create a symbolic link with this command:**
```
sudo ln -s /usr/lib/libiomp5.so.5 /usr/lib/libiomp5.so
```
**Insert license key:**
```
sudo mopac "xxxxxxxxxxxxxxxx"
```
# RUN MOPAC
```
scons variant=POA1_1.18 POA1_1.18/MOLECULE.cos
```