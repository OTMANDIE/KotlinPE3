package com.example.correctioncontrolekt

import java.util.Objects

open class Produit (private var code:Int,private var prix:Float){

    fun getCode(): Int {
        return code
    }

    fun getPrix(): Float {
        return prix
    }

    fun setCode (code:Int){
        this.code = code
    }

    fun setPrix (prix:Float){
        this.prix = prix
    }

    open fun prixProduit():Float{
        return this.prix
    }

    override fun toString(): String {
        return "Code produit: $code , Prix produit: $prix"
    }

    fun equals(obj: Objects):Boolean {
        if ((obj as Produit).getCode() == code) {
            return true
        }
        return false
    }
}
class ProduitEnSolde(code: Int,prix: Float ,private var remise:Int):Produit(code,prix){

    fun getRemise() = remise

    fun setRemise(remise:Int){
        this.remise = remise
    }

    override fun prixProduit(): Float {
        return getPrix()*(100-remise/100)
    }

    override fun toString(): String {
        return super.toString()+" Remise: $remise"
    }

}
class Boutique {

    private var produits: ArrayList<Produit> = ArrayList()

    fun indiceDe(code:Int):Int{
        for (i in 0 until produits.size){
            if (produits.get(i).getCode()==code){
                return i
            }
        }
        return -1
    }

    fun ajouter(p:Produit){
        if (indiceDe(p.getCode())==-1){
            produits.add(p)
        }
    }

    fun supprimer(code:Int):Boolean{
        if (indiceDe(code)!=-1){
            produits.removeAt(indiceDe(code))
            return true
        }
        return false
    }

    fun supprimer(p:Produit):Boolean{
        if (supprimer(p.getCode())){
            return true
        }
        return false
    }

    fun nombreProduitEnSolde():Int {
        var nbProduit: Int = 0
        for (p in produits) {
            if (p is ProduitEnSolde) {
                nbProduit++
            }
        }
        return nbProduit
    }
}
fun main(){
    var p1 = ProduitEnSolde(123,400.34f,50)
    var b1 = Boutique()
    p1.toString()
    b1.ajouter(p1)
    println(b1.nombreProduitEnSolde())
}
























