# LVPP sigma-profile database
[![DOI](https://zenodo.org/badge/DOI/10.5281/zenodo.3924076.svg)](https://doi.org/10.5281/zenodo.3924076)
[![DOI](https://zenodo.org/badge/DOI/10.5281/zenodo.3613786.svg)](https://doi.org/10.5281/zenodo.3613786)
[![DOI](https://img.shields.io/badge/DOI-10.1021%2Facs.jctc.5c01368-blue)](https://doi.org/10.1021/acs.jctc.5c01368)

This is the LVPP sigma-profile database for COSMO-based models. It currently contains:
 - Ready to use COSMO surface charge densities for more than **2500 molecules**.
 - [**COSMO-SAC parametrizations**](./pars/) for different variants.
 - [**Optimized gas phase geometries**](./geometry/).

In the latest version, COSMO surface charge densities are computed with [NWChem](https://nwchemgit.github.io/).
Previous versions used [GAMESS](http://www.msg.ameslab.gov/gamess/) and [MOPAC](http://openmopac.net/).

You will still need [JCOSMO](https://www.ufrgs.br/lvpp/download/jcosmo/) or other software to actually compute activity coefficients or other properties with models like COSMO-RS or COSMO-SAC.

## Citing
We kindly ask you to cite this work as:
 * "[Recent Improvements to the NWChem COSMO Module](https://doi.org/10.1021/acs.jctc.5c01368)", DOI:10.1021/acs.jctc.5c01368
 * "[LVPP sigma-profile database (20.06)](https://doi.org/10.5281/zenodo.3924076)", DOI:10.5281/zenodo.3924076
 * "[LVPP sigma-profile database (18.07)](https://doi.org/10.5281/zenodo.3613786)", DOI:10.5281/zenodo.3613786
 * "[An open and extensible sigma-profile database for COSMO-based models](https://doi.org/10.1002/aic.16194)", F. Ferrarini, G. B. Flores, A. R. Muniz, and R. de P. Soares (2018), AIChE J.
 * "[Assessing the reliability of predictive activity coefficient models for molecules consisting of several functional groups](http://dx.doi.org/10.1590/S0104-66322013000100002)", R. P. Gerber and R. P. Soares (2013), Braz. J. Chem. Eng. Vol. 30, No. 01

## Using
The *processed* database is made available in ZIP files, check the [releases](http://github.com/lvpp/sigma/releases).
Just download one of the release files, uncompress and look for the molecule you need.
[JCOSMO](https://www.ufrgs.br/lvpp/download/jcosmo/) can be used to view the 3D apparent surface charge densities, sigma-profiles, phase equilibrium calculations and more.

## Extending the database

There are different methods to extend the database.

### Ask LVPP to add new molecules

Just create and [new issue](https://github.com/lvpp/sigma/issues)
and describe the molecules you need that are missing.
If possible, also attach a **3D mol** file for each molecule you want added.
It can take a while until we process your request.

### Process your own molecules

If you can't wait for us to process your molecules (or if you don't want to)
you can can **process** your own molecules.

Just **clone** or [download](https://github.com/lvpp/sigma/archive/master.zip) this repository and then
please check the [cosmo folder](cosmo) for instructions
on how to process your own molecules.

Our instructions are for Ubuntu Linux 24.04 or superior. They should also work on other similar systems.

## License

The LVPP sigma-profile database and all the documents distributed in this repository are distributed under the terms
of the [MIT License](https://github.com/lvpp/sigma/blob/master/LICENSE).
