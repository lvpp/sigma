# GMHB1808 COSMO-SAC parametrization
A COSMO-SAC **G**AMESS parametrization with **M**ulti-**H**ydrogen **B**ond energies, release 20**18**/**11**

## Parameters considered

Parameter | Value
--------- | -------------
fpol | 0.9164830865745204
rAvg | 1.1
rEff | 1.1601900201971949
cHB | 15140.62207443747
cHB2 | 16364.236912538769
cHB3 | 9537.801629319063
cHB4 | 14434.540207856822
cHB5 | 14019.767919120282
cHB6 | 7785.47125140889
cHB7 | 4971.049004916798
cHB8 | 14171.015505398
sigmaHB | 7544.14516771321

## Averaging
The apparent surface charges from GAMESS were averaged using the rAvg listed above and a
[fdecay](http://www.doi.org/10.1016/j.fluid.2010.06.011)=3.57

## Interactions
Segment interactions were given by the sum of electrostatic and hydrogen bond.

The activity coefficient of a compound was computed using aEff:
 - aEff = PI*rEff^2
 - lnGamma_i += sum_m (area(m)/aEff)*(lnSegGammaMix(m) - lnSegGammaPure(m));

### Electrostatic term
For the electrostatic interactions:
 - aAvg = PI*rAvg^2
 - alpha = 0.3/E0 * aAvg^1.5
 - deltaW = fpol*alpha/2.0 * (sigma_m + sigma_n)^2
 
## Hydrogen bond
**Only H atoms bonded to oxygen** atoms were considered HB donors (require extra hydrogen bond correction
beyond the electrostatic contribution).

The standard formulation was assumed:
 - Ehb = cHBi * (max(0, sigmaAcc - sigmaHB)*min(0, sigmaDon + sigmaHB))

where sigmaHB is the parameter listed above and sigmaAcc and sigmaDon are the apparent surface charge
densities of the acceptor and donor segments.

Different hydrogen bond energies cHBi were considered here:

Parameter | H-Donor | H-Acceptor
--------- | ------|--------
 cHB | water | water
 cHB2| water | is bonded to H
 cHB3| water | is not bonded to H (e.g. acetone)
 cHB7| water | is bonded to more than 2 atoms (e.g. ether)
 cHB4| not water | water
 cHB5| not water | atom bonded to H
 cHB6| not water | not bonded to H (e.g. acetone)
 cHB8| not water | is bonded to more than 2 atoms (e.g. ether)
 
## Combinatorial contribution
For the combinatorial contribution **only the Flory-Huggins** term was considered,
the Staverman-Guggenheim correction was ignored.

## Resulting fit

The above parameters were optimized using a test set detailed below.
The fit resulted in an R2=0.9810 for 1470 IDAC experimental points.

![diag](https://github.com/lvpp/sigma/raw/master/pars/GMHB1811/diag.png)

## Molecule files considered in the parametrization

| | | |
|-|-|-|
1,2-DICHLOROETHANE.gout  |    N-BUTANOL.gout |  WATER.gout|
1-BUTENE.gout             |   N-BUTYRALDEHYDE.gout | CARBON_TETRACHLORIDE.gout  
1-HEPTENE.gout             |  CHLOROFORM.gout         |   N-BUTYRONITRILE.gout
1-HEXENE.gout               | CYCLOHEXANE.gout        |   N-DECANE.gout
2,2,3-TRIMETHYLBUTANE.gout  | DIETHYL_ETHER.gout      |   N-HEPTANE.gout
2,2,4-TRIMETHYLPENTANE.gout | DIISOPROPYL_ETHER.gout  |   N-HEXADECANE.gout
2,3,4-TRIMETHYLPENTANE.gout | DIMETHYL_ETHER.gout     |   N-HEXANE.gout
2,4-DIMETHYLPENTANE.gout    | ETHANOL.gout            |   N-NONANE.gout
2-METHYLPENTANE.gout        | ETHYL_ACETATE.gout      |   N-OCTANE.gout
ACETONE.gout                | ISOBUTYRALDEHYDE.gout   |   N-PENTANE.gout
METHYL_ACETATE.gout        |TETRAHYDROFURAN.gout  |  METHYL_N-BUTYL_ETHER.gout
ANILINE.gout               |  METHYLCYCLOHEXANE.gout   |  TOLUENE.gout
BENZENE.gout               |  METHYL_ETHYL_KETONE.gout  | TRIETHYLAMINE.gout

