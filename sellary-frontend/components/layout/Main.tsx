import React from 'react';
const Main = ({ children }: {
    children: React.ReactNode;
}) => {
    return(
        <>
            <div className='m-2 rounded-m p-4'>
            {children}
            </div>
        </>
    )
}
export default Main;