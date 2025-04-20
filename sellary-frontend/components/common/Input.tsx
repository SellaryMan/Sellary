import React from "react";

interface InputBaseProps {
  label?: string;
  isRequired?: boolean;
}

const FlexBox =({children}:{children:React.ReactNode})=>{
  return(
    <div className="flex w-full">
      {children}
    </div>
  )
}
const Input = {
  Text: React.forwardRef<
    HTMLInputElement, 
    InputBaseProps & React.InputHTMLAttributes<HTMLInputElement>
  >(({ label, isRequired, className, ...props }, ref) => {
    return (
      <FlexBox>
        {label && (
          <label>
            {isRequired && <span>*</span>}
            {label}
          </label>
        )}
        <input
        className={className}
          ref={ref}
          {...props}
        />
      </FlexBox>
    );
  }),
}
export default Input;