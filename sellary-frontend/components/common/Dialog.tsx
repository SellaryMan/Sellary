'use client'
import React from "react";
interface OpenProps {
    isOpen : boolean,
    closeDialog: ()=>void;
}
const Dialog ={
    OpenTrigger: React.forwardRef<HTMLDivElement, React.HTMLAttributes<HTMLDivElement>>(({ ...props},ref) => {
        return (
            <div ref={ref} {...props}>
                {props.children}
            </div>
        );
    }),
    Content : React.forwardRef<
        HTMLDivElement, 
        React.HTMLAttributes<HTMLDivElement>
    >(({ children, ...props }, ref) => {
        return (
        <div ref={ref} {...props}>
            {children}
        </div>
        );
    }),
    DialogContent: React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement> & OpenProps
>(({ children, isOpen, closeDialog, ...props }, ref) => {
    return isOpen && (
        <>
            <div 
                className="z-9999 fixed inset-0 bg-black opacity-50" 
                onClick={closeDialog}
            />
            <div 
                className="z-9999 fixed inset-0 flex items-center justify-center pointer-events-none"
                ref={ref}
                {...props}
            >
                <div className="pointer-events-auto">
                    {children}
                </div>
            </div>
        </>
    );
}),
}

export default Dialog;
    