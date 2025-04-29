'use client'
import { useState } from "react";
interface IUseDialog {
    closeDialog : ()=>void;
    openDialog : ()=>void;
    isOpen : boolean
}
const useDialog =():IUseDialog=>{
    const [isOpen,setIsOpen] = useState(false);

    const closeDialog =()=> setIsOpen(false);
    const openDialog =()=> setIsOpen(true);

    return {
        isOpen, closeDialog, openDialog
    }
    
}
export default useDialog
